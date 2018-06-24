from flask import Flask
from flask_restful import Resource, reqparse, fields, marshal, abort, Api

app = Flask(__name__, static_url_path="")
api = Api(app)

goods = [
    {
        'Id': 0,
        'price': 0,
        'weight': 0,
        'color': 'Default'
    }
]

goods_fields = {
    'id': fields.Integer,
    'price': fields.Integer,
    'weight': fields.Integer,
    'color': fields.String,
}


class Good(Resource):
    def __init__(self):
        self.reqparse = reqparse.RequestParser()
        self.reqparse.add_argument('id', type=int, required=True, help='No Id provided', location='json')
        self.reqparse.add_argument('price', type=int, location='json')
        self.reqparse.add_argument('weight', type=int, location='json')
        self.reqparse.add_argument('color', type=str, location='json')
        super(Good, self).__init__()  # super().__init__() / Good.__init__(self)

    def put(self):
        args = self.reqparse.parse_args()
        good = {
            'Id': goods[-1]['Id'] + 1,
            'id': args['id'],
            'price': args['price'],
            'weight': args['weight'],
            'color': args['color']
        }
        goods.append(good)
        return {'Good': marshal(goods, goods_fields)}, 201

    def get(self, id):
        good = [good for good in goods if good.get('id') == id]
        if len(good) == 0:
            abort(404)
        return {'Good': marshal(good[0], goods_fields)}

    def delete(self, id):
        good = [good for good in goods if good.get('id') == id]
        if len(good) == 0:
            abort(404)
        goods.remove(good[0])
        return {'result': True}

    def post(self):
        args = self.reqparse.parse_args()
        good = [good for good in goods if good.get('id') == args['id']]
        if len(goods) == 0:
            abort(404)
        good = good[0]
        args = self.reqparse.parse_args()
        for k, v in args.items():
            if v is not None:
                good[k] = v


api.add_resource(Good, '/goods', endpoint='goods')
api.add_resource(Good, '/goods/<int:id>', endpoint='good')

if __name__ == '__main__':
    app.run(debug=True)
