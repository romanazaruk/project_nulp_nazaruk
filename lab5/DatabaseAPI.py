import configparser
from flask_sqlalchemy import SQLAlchemy
from flask import Flask, jsonify, request

application = Flask(__name__)

config = configparser.ConfigParser()
config.read('goods_db.config')

application.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://' + config.get('DB', 'user') + \
                                                ':' + config.get('DB', 'password') + '@' + \
                                                config.get('DB', 'host') + '/' + config.get('DB', 'db')

application.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = True

mysql = SQLAlchemy()
mysql.init_app(application)


class Goods(mysql.Model):
    __tablename__ = 'good'
    id = mysql.Column(mysql.Integer, primary_key=True)
    name = mysql.Column(mysql.String(128), nullable=False)
    weight = mysql.Column(mysql.Float, nullable=False)
    price = mysql.Column(mysql.Float, nullable=False)
    amount = mysql.Column(mysql.Integer, nullable=False)

    def __repr__(self):
        return '<Goods (%s, %s, %s, %s) >' % (self.name, self.weight, self.price, self.amount)


@application.route("/")
def hello():
    return "Hello World!"


@application.route('/good', methods=['POST'])
def create_good():
    id = request.get_json()["id"]
    name = request.get_json()["name"]
    weight = request.get_json()["weight"]
    price = request.get_json()["price"]
    amount = request.get_json()["amount"]
    curr_session = mysql.session

    good = Goods(id=id,  name=name, weight=weight, price=price, amount=amount)

    try:
        curr_session.add(good)
        curr_session.commit()
    except:
        curr_session.rollback()
        curr_session.flush()

    good_id = good.id
    data = Goods.query.filter_by(id=good_id).first()

    config.read('goods_db.config')

    result = [data.name, data.weight, data.price, data.amount]

    return jsonify(session=result)


@application.route('/good', methods=['GET'])
def get_good():
    data = Goods.query.all()

    data_all = []

    for good in data:
        data_all.append([good.id, good.name, good.weight, good.price, good.amount])

    return jsonify(goods=data_all)


@application.route('/good', methods=['PATCH'])
def update_good():
    global good
    good_id = request.get_json()["id"]
    weight = request.get_json()["weight"]
    name = request.get_json()["name"]
    price = request.get_json()["price"]
    amount = request.get_json()["amount"]
    curr_session = mysql.session

    try:
        good = Goods.query.filter_by(id=good_id).first()
        good.weight = weight
        good.name = name
        good.price = price
        good.amount = amount
        curr_session.commit()
    except:
        curr_session.rollback()
        curr_session.flush()

    good_id = good.id
    data = Goods.query.filter_by(id=good_id).first()

    config.read('goods_db.config')

    result = [data.name, data.weight, data.price, data.amount]

    return jsonify(session=result)


@application.route('/good/<int:good_id>', methods=['DELETE'])
def delete_good(good_id):
    curr_session = mysql.session

    Goods.query.filter_by(id=good_id).delete()
    curr_session.commit()

    return get_good()


if __name__ == "__main__":
    application.run()