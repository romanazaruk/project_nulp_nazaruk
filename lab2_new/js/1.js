var useLocalStorage = false;

//prefixes of implementation that we want to test
window.indexedDB = window.indexedDB || window.mozIndexedDB ||
window.webkitIndexedDB || window.msIndexedDB;

//prefixes of window.IDB objects
window.IDBTransaction = window.IDBTransaction ||
window.webkitIDBTransaction || window.msIDBTransaction;
window.IDBKeyRange = window.IDBKeyRange || window.webkitIDBKeyRange ||
window.msIDBKeyRange

if (!window.indexedDB) {
    window.alert("Your browser doesn't support a stable version of IndexedDB.")
 }

 const fansData = [];
 const newsData = [];
 var db;
 var request = window.indexedDB.open("newDatabase", 1);
 console.log("request");
 request.onerror = function(event) {
    console.log("error: ");
 };

 request.onsuccess = function(event) {
    db = request.result;
    console.log("success: "+ db);

 };

 request.onupgradeneeded = function(event) {
    var db = event.target.result;
    var objectStoreFans = db.createObjectStore("fans", {keyPath: "id"});
    console.log("created");
    for (var i in fansData) {
        objectStoreFans.add(fansData[i]);
    }

    var objectStoreNews = db.createObjectStore("news", {keyPath: "id"});

    for (var i in fansData) {
        objectStoreNews.add(newsData[i]);
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
        var count = parseInt(localStorage.getItem('count-fan'));
        if (isNaN(count)) count = 0;
        var request = db.transaction(["fan"], "readwrite")
            .objectStore("fan")
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
                    name: `${(this.fantext)}`
                }), // Coordinate the body type with 'Content-Type'

            })
            .then(response => response.json())
            .then(res => console.log(res));
    }

}


function addFan() {
    var fan1;
    fan1 = new Fan();
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
    document.getElementById("message").value = "";

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
            var fan1 = new Fan();
            fan1.fan = request.result.message;
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
                var fan1 = new Fan();
                fan1.fan = data[k].name + ' ' + data[k].Created_date;
                console.log(fan1);
                fan1.append();
            }

        })
        .catch(error => console.error(error))

}

function addToServerFromLocal() {
    var objectStore = db.transaction(["fan"],"readwrite").objectStore('fan');
    var myIndex = objectStore.count();


    var count = parseInt(localStorage.getItem('count-fan'));
    var message;
    if (isNaN(count)) count = 0;
    var news1 = new News();
    var i;
    var fan1 = new Fan();
    console.log("SizeLocalStorage:"+count);
    for (i = 0; i < count; i++) {

        fan1.fan = localStorage.getItem("fan-item-" + i);
        console.log(i,  localStorage.getItem("fan-item-" + i));
        fan1.addToServer();
        fan1.append();
    }
    localStorage.clear();

    myIndex.onsuccess = function() {
        count = parseInt(myIndex.result);
        console.log("Size indexedDB:" + count);

        i = 0;
        fan1 = new Fan();

        for (i = 0; i < count; i++) {
            var z = readFan(objectStore, i);
            fan1.fan = z;
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
        if(isNaN(count)) count =0;
        var request = db.transaction(["news"], "readwrite")
                        .objectStore("news")
                        .add({id: count, message: `${(this).toString()}`});

        request.onsuccess = function(event) {
           alert("Prasad has been added to your database.");
        };

        request.onerror = function(event) {
           alert("Unable to add data\r\nPrasad is already exist in your database! ");
        }

        localStorage.setItem("count-news", (count + 1));
     }
     addToServer(){
        window.addEventListener("online", function (e) {
            console.log("online");
            $.post("demo_test_post.asp", {
                date: this.date,
                message: this.newsText
            });
        })
     }
}

function addNews() {

    news1 = new News();
    console.log(news1.toString());

    if (isOnline()){
        news1.addToServer();
        //news1.append();
    } else {
        if (useLocalStorage){
            news1.addToLocalStorage();
        } else
        {
            news1.addToIndexedDB();
        }

    }
    document.getElementById("news-text").value = "";
     document.getElementById("news-title").value ="";

}
function readNews(id) {

    var transaction = db.transaction(["news"]);
    var objectStore = transaction.objectStore("news");
    var request = objectStore.get(id);
    var result;
    request.onerror = function(event) {
       alert("Unable to retrieve daa from database!");
    };

    request.onsuccess = function(event) {

    if(request.result) {
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

function displayNewsOnline(){
    var count = parseInt(localStorage.getItem('count-news'));
    var message;
    console.log(count);
    var news1 = new News();
    for (i = 0; i < count; i++ ) {


         console.log(i);
        if (useLocalStorage) {

            news1.news = localStorage.getItem("news-item-"+i);
            console.log(message);
            news1.append();
        } else {
            readNews(i);

        }
        console.log(news1.toString());
       // news1.append();
        console.log("+1");
        console.log("news-item-" + i);
    }

}

function deletenews() {

    var i;
    for (i > 0; i < count; i++ ) {
        localStorage.removeItem('news-item-' + i)
    }
    localStorage.removeItem('count-news');
}

window.addEventListener('load', function () {

    function updateOnlineStatus(event) {
    }

    if(isOnline()){
    window.addEventListener('online',  displayNewsOnline() );
    window.addEventListener('online',  displayFansOnline() );
    } else {
    window.addEventListener('offline',displayNewsOffline() );
    window.addEventListener('offline',  displayFansOffline());
    }
});
