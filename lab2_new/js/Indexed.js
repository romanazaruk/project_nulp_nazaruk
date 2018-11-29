'use strict';
var useLocalStorage = true;

if (typeof(window) !== 'undefined') {

    if (!window.indexedDB) {
        window.alert("Your browser doesn't support a stable version of IndexedDB.")
    }


    const fanData = [];
    const newsData = [];
    var db;


    var request = window.indexedDB.open("newDatabase", 1);


    console.log("request");
    request.onerror = function(event) {
        console.log("error: ");
    };

    request.onsuccess = function(event) {
        db = request.result;
        console.log("success: " + db);


    };

    request.onupgradeneeded = function(event) {
        var db = event.target.result;
        var objectStoreFan = db.createObjectStore("fans", {
            keyPath: "id"
        });
        console.log("created");
        for (var i in fanData) {
            objectStoreFan.add(fanData[i]);
        }

        var objectStoreNews = db.createObjectStore("news", {
            keyPath: "id"
        });

        for (var i in fanData) {
            objectStoreNews.add(newsData[i]);
        }

    }
}

function isOnline() {
    return window.navigator.onLine;
}

class Fans{

    constructor(){
        try{
        var nowDate = new Date();
        var message = document.getElementById("feedback-text").value;
    } catch (e)
    {
        var message ="";
        var title ="";
    }
        this.fanstext = message;
        this.date = nowDate.getDate() + "." + (nowDate.getMonth() + 1) + "." + nowDate.getFullYear();
    }
    set fans(text){
        console.log("text: "+text);
        [this.fanstext, this.date] = text.split(' ');
    }
    toString(){
        return `${(this.fanstext)} ${(this.date)}`;
    }
    append() {
        var original = document.getElementById("feedback-container");
        var fans = document.createElement("div");
        fans.class="request";
        fans.innerHTML=
        " <p>" + `${(this.fanstext)}` + " </p>" +
        "<div class=\"req-footer\">"+
        " <div class=\"date\">"+`${(this.date)}` + "</div>" ;

        original.appendChild(fans);
        document.getElementById("feedback-text").value = "";
    }
    check(){
        alert("The field is empty!");
        if (message == "") {
            document.getElementById("feedback-text").style.borderColor = "red";
        }
    }
    addToLocalStorage(){
        var count = parseInt(localStorage.getItem('count-fans'));
        if(isNaN(count)) count =0;

        localStorage.setItem("fans-item-" + count, `${(this).toString()}`);
        console.log( `${(this).toString()}`);
        localStorage.setItem("count-fans", (count + 1));
    }

    addToIndexedDB() {
        var count = parseInt(localStorage.getItem('count-fans'));
        if (isNaN(count)) count = 0;
        var request = db.transaction(["fans"], "readwrite")
            .objectStore("fans")
            .add({
                id: count,
                message: `${(this).toString()}`
            });

        request.onsuccess = function(event) {
            alert("Prasad has been added to your database.");
        };

        request.onerror = function(event) {
            alert("Unable to add data\r\nPrasad is already exist in your database! ");
        }

        localStorage.setItem("count-fan", (count + 1));
    }
    addToServer() {
          console.log(JSON.stringify({
            name: `${(this)}`
        }));
        return fetch('http://localhost:8000/feedbacks/', {
                credentials: 'same-origin', // 'include', default: 'omit'
                method: 'POST', // 'GET', 'PUT', 'DELETE', etc.
                headers: new Headers({
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                    'Access-Control-Request-Method': 'POST',
                    'Access-Control-Request-Headers': 'content-type'
                }),
                body: JSON.stringify({
                    name: `${(this)}`
                }), // Coordinate the body type with 'Content-Type'

            })
            .then(response => response.json())
            .then(res => console.log(res));
    }

}


function addFans() {
    var fan1;
    fan1 = new Fans();
    console.log(fan1.toString());

    if (isOnline()) {

        fan1.addToServer();
        fan1.append();
    } else {
        if (useLocalStorage) {
            fan1.addToLocalStorage();
        } else {
            fan1.addToIndexedDB();
        }

    }


}

function readFan(objectStore, id) {

    // var tranaction = db.tranaction(["fan"]);
    // var objectStore = tranaction.objectStore("fan");
    var request = objectStore.get(id);
    var result;
    request.onerror = function(event) {
        alert("Unable to retrieve daa from database!");
    };

    request.onsuccess = function(event) {

        if (request.result) {
            var fan1 = new Fans();
            fan1.fans = request.result.message;
            fan1.append();
            fan1.addToServer();
            return request.result.message;
        } else {
            alert("Kenny couldn't be found in your database!");
        }
    };
    return "";
}

function getFromServer() {

    return fetch('http://localhost:8000/feedbacks')
        .then(response => response.json())
        .then(data => {
            console.log(data); // Prints result from `response.json()` in getRequest
            for (var k in data) {
                var fan1 = new Fans();
                fan1.fans = data[k].name + ' ' + data[k].Created_date;
                console.log(fan1);
                fan1.append();
            }

        })
        .catch(error => console.error(error))

}

function addToServerFromLocal() {

    var count = parseInt(localStorage.getItem('count-fans'));
    var message;
    if (isNaN(count)) count = 0;
    var news1 = new News();
    var i;
    var fan1 = new Fans();
    console.log("SizeLocalStorage:"+count);
    for (i = 0; i < count; i++) {

        fan1.fans = localStorage.getItem("fans-item-" + i);
        console.log(i,  localStorage.getItem("fans-item-" + i));
        fan1.addToServer();
        fan1.append();
    }
    localStorage.clear();

    var objectStore = db.transaction(["fans"],"readwrite").objectStore('fans');
    var myIndex = objectStore.count();


    myIndex.onsuccess = function() {
        count = parseInt(myIndex.result);
        console.log("Size indexedDB:" + count);

        i = 0;
        fan1 = new Fans();

        for (i = 0; i < count; i++) {
            var z = readFan(objectStore, i);
            fan1.fans = z;
            console.log(fan1.toString());
            fan1.append();

        }
    }
    var objectStoreRequest = objectStore.clear();

    objectStoreRequest.onsuccess = function(event) {
        // alert('success');
    }

}

function displayFanOnline() {
    getFromServer();
    addToServerFromLocal();

}

function deletefan() {

    var i;
    for (i > 0; i < count; i++) {
        localStorage.removeItem('fan-item-' + i)
    }
    localStorage.removeItem('count-fan');
}


// NEWS

class News{

    constructor(){
        var nowDate = new Date();
        try {
        var message = document.getElementById("news-title").value;
        var title = document.getElementById("news-text").value;
        } catch (e)
        {
            var message ="";
            var title ="";
        }
        this.newsText = message;
        this.date = title;
    }
    set news(text){
        console.log("text: "+text);
        [this.newsText, this.date] = text.split(' ');
    }
    toString(){
        return `${(this.newsText)} ${(this.date)}`;
    }
    append() {
        var original = document.getElementById("news-div");
        var news1 = document.createElement("div");
        news1.className= "col-lg-3 col-md-4 col-sm-6 ";
        var news = document.createElement("div");
        news.className= "news-block";
        news.innerHTML=
        " <img src = \"assets/images/news4.jpg\"class = \"news-img\" >  </img > " +
        "<div class= \"news-content \">"+
        "<h3>" + `${(this.date)}`  + "</h3> " +
        "<p>" + `${(this.newsText)}` + "</p> </div>";
        original.appendChild(news1);
        news1.appendChild(news);

    }
    check(){
        alert("The field is empty!");
        if (message == "") {
            document.getElementById("feedback-text").style.borderColor = "red";
        }
    }
    addToLocalStorage(){
        var count = parseInt(localStorage.getItem('count-news'));
        if(isNaN(count)) count =0;

        localStorage.setItem("news-item-" + count, `${(this).toString()}`);
        console.log( `${(this).toString()}`);
        localStorage.setItem("count-news", (count + 1));
    }

    addToIndexedDB() {
        var count = parseInt(localStorage.getItem('count-news'));
        if (isNaN(count)) count = 0;
        var request = db.tranaction(["news"], "readwrite")
            .objectStore("news")
            .add({
                id: count,
                message: `${(this).toString()}`
            });

        request.onsuccess = function(event) {
            alert("Prasad has been added to your database.");
        };

        request.onerror = function(event) {
            alert("Unable to add data\r\nPrasad is already exist in your database! ");
        }

        localStorage.setItem("count-news", (count + 1));
    }
    addToServer() {
        console.log(JSON.stringify({
            name: `${(this)}`
        }));
        return fetch('http://localhost:8000/feedbacks/', {
                credentials: 'same-origin', // 'include', default: 'omit'
                method: 'POST', // 'GET', 'PUT', 'DELETE', etc.
                headers: new Headers({
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                    'Access-Control-Request-Method': 'POST',
                    'Access-Control-Request-Headers': 'content-type'
                }),
                body: JSON.stringify({
                    name: `${(this.newsText)}`,
                    title: `${(this.date)}`
                }), // Coordinate the body type with 'Content-Type'

            })
            .then(response => response.json())
            .then(res => console.log(res));
    }
}

function addNews() {

    var news1 = new News();
    console.log(news1.toString());

    if (isOnline()) {
        console.log('Isonline');
        news1.addToServer();
        //news1.append();
    } else {
        if (useLocalStorage) {
            news1.addToLocalStorage();
        } else {
            news1.addToIndexedDB();
        }

    }
    document.getElementById("texts").value = "";
    document.getElementById("title").value = "";

}

function readNews(id) {

    var tranaction = db.tranaction(["news"]);
    var objectStore = tranaction.objectStore("news");
    var request = objectStore.get(id);
    var result;
    request.onerror = function(event) {
        alert("Unable to retrieve daa from database!");
    };

    request.onsuccess = function(event) {

        if (request.result) {
            var news1 = new News();
            news1.news = request.result.message;
            news1.append();

            console.log(request.result.message);
            return request.result.message;
        } else {
            alert("Kenny couldn't be found in your database!");
        }
    };
    return "";
}
function getNewsFromServer() {

    return fetch('http://localhost:8000/feedbacks')
        .then(response => response.json())
        .then(data => {
            console.log(data); // Prints result from `response.json()` in getRequest
            for (var k in data) {
                if(data[k].title!='title') {
                    var news1 = new News();
                    news1.news =  data[k].title+ ' '+data[k].name ;
                    console.log(news1);
                    news1.append();
                }
            }

        })
        .catch(error => console.error(error))

}


function addNewsToServerFromLocal() {
    var objectStore = db.transaction(["news"],"readwrite").objectStore('news');
    var myIndex = objectStore.count();


    var count = parseInt(localStorage.getItem('count-news'));
    var message;
    if (isNaN(count)) count = 0;
    var news1 = new News();
    var i;
    console.log("SizeLocalStorage:"+count);
    for (i = 0; i < count; i++) {

        news1.news = localStorage.getItem("news-item-" + i);
        console.log(i,  localStorage.getItem("news-item-" + i));
        news1.addToServer();
        news1.append();
    }
    localStorage.clear();

    myIndex.onsuccess = function() {
        count = parseInt(myIndex.result);
        console.log("Size indexedDB:" + count);

        i = 0;
        news1 = new News();

        for (i = 0; i < count; i++) {
            var z = readNews(objectStore, i);
            news1.news = z;
            console.log(news1.toString());
            news1.append();

        }
    }
    var objectStoreRequest = objectStore.clear();

    objectStoreRequest.onsuccess = function(event) {
        // alert('success');
    }

}

function displayNewsOnline() {
    getNewsFromServer();
    addNewsToServerFromLocal();

}

function deletenews() {

    var i;
    for (i > 0; i < count; i++) {
        localStorage.removeItem('news-item-' + i)
    }
    localStorage.removeItem('count-news');
}


window.addEventListener('load', function () {

    function updateOnlineStatus(event) {
    }

    if(isOnline()){
    window.addEventListener('online',  displayNewsOnline() );
    window.addEventListener('online',  displayFanOnline() );
    } else {
    window.addEventListener('offline',displayNewsOffline() );
    window.addEventListener('offline',  displayFanOffline());
    }
});
