var now = new Date();
var currentYear = now.getFullYear();    // 지금 년도        
var nowMonth = now.getMonth();          // 지금 달 - 1

// 이전 달의 마지막 날 날짜와 요일 구하기
var startDay = new Date(currentYear, nowMonth, 0);
var prevDate = startDay.getDate();
var prevDay = startDay.getDay();

// 이번 달의 마지막날 날짜와 요일 구하기
var endDay = new Date(currentYear, nowMonth + 1, 0);
var nextDate = endDay.getDate();
var nextDay = endDay.getDay();

sessionStorage.setItem('year', currentYear);
sessionStorage.setItem('month', nowMonth+1);

var y = sessionStorage.getItem('year');
var m = sessionStorage.getItem('month');

document.getElementById('year').innerHTML = y;
document.getElementById('month').innerHTML = m;

// 1일 부터 월 말까지 달력 그리기
const dayListDiv = document.createElement('div');
dayListDiv.className = 'dayList';
document.body.appendChild(dayListDiv);

var count = 0;

// 1일이 시작하는 요일 전까지는 빈 공백으로 그리기
for(var i = 0; i <= prevDay; i++) {
    const divElement = document.createElement('div');
    divElement.className = 'dayNum';
    dayListDiv.appendChild(divElement);
    count++;
}

for(var i = 1; i <= nextDate; i++) {
    const divElement = document.createElement('div');
    divElement.className = 'dayNum';
    divElement.innerHTML = i +". ";
    
    if (count % 7==6){
        divElement.style.color = "blue";
    } else if(count % 7==0) {
        divElement.style.color = "red";
    }

    dayListDiv.appendChild(divElement);

    const inputElement = document.createElement('input');
    inputElement.setAttribute("type", "text");
    inputElement.setAttribute("placeholder", "Todo")
    inputElement.id = 'todoInput' + i;

    const enrollButton = document.createElement("input");
    enrollButton.id = 'enrollButton' + i;
    enrollButton.setAttribute("type", "button");
    enrollButton.setAttribute('value', '등록');

    day = 'enroll(' + i +')';
    enrollButton.setAttribute('onclick', day);

    const ul = document.createElement('ul');
    ul.setAttribute('id', 'doList'+i);

    const allDeleteButton = document.createElement("input");
    allDeleteButton.id = 'allDeleteButton' + i;
    allDeleteButton.setAttribute("type", "button");
    allDeleteButton.setAttribute('value', '전부삭제');
    allDeleteButton.setAttribute('onclick', 'allDelete('+i+')');

    dayListDiv.appendChild(inputElement);
    divElement.appendChild(inputElement);
    divElement.appendChild(enrollButton);
    divElement.appendChild(allDeleteButton);
    divElement.appendChild(ul);

    count++;
}

function enroll(day) {
    wantDay = 'todoInput' + day;
    const addValue = document.getElementById(wantDay).value;

    window.localStorage.setItem(wantDay+'/'+addValue,addValue);
    
    const li = document.createElement('li');
    li.setAttribute('id', 'todo');
    li.setAttribute('onclick', 'oneDelete('+ addValue +', '+ day +')');

    const textNode = document.createTextNode(addValue);
    li.appendChild(textNode);

    document.getElementById('doList'+day).appendChild(li);
}

function allDelete(day) {
    const temp = document.getElementById('doList'+day);

    const items = temp.getElementsByTagName('li');

    var len = items.length;
    for (var i = 0; i < len; i++) {
        items[0].remove();
    }
}

function oneDelete(str, day) {
    var res = confirm('삭제하시겠습니까?');
    if (res== true) {   // 확인 버튼 클릭
        const temp = document.getElementById('doList' + day);
        const items = temp.getElementsByTagName('li');

        var len = items.length;

        for(var i = 0; i < len; i++) {
            if (items[i].innerText == str) {
                items[i].remove();
            }
        }
    } else {
    }
}

const userId = "gs";

window.addEventListener("DOMContentLoaded",function(){
    getEvents(userId,"2022-09-01");
    getEvents(userId,"2022-09-02");
    getEvents(userId,"2022-09-03");
    getEvents(userId,"2022-09-04");
    getEvents(userId,"2022-09-05");
    getEvents(userId,"2022-09-06");
    getEvents(userId,"2022-09-07");
    getEvents(userId,"2022-09-08");
    getEvents(userId,"2022-09-09");
    getEvents(userId,"2022-09-10");
    getEvents(userId,"2022-09-12");
    getEvents(userId,"2022-09-12");
    getEvents(userId,"2022-09-13");
    getEvents(userId,"2022-09-14");
    getEvents(userId,"2022-09-15");
    getEvents(userId,"2022-09-16");
    getEvents(userId,"2022-09-17");
    getEvents(userId,"2022-09-18");
    getEvents(userId,"2022-09-19");
    getEvents(userId,"2022-09-20");
    getEvents(userId,"2022-09-21");
    getEvents(userId,"2022-09-22");
    getEvents(userId,"2022-09-23");
    getEvents(userId,"2022-09-24");
    getEvents(userId,"2022-09-25");
    getEvents(userId,"2022-09-26");
    getEvents(userId,"2022-09-27");
    getEvents(userId,"2022-09-28");
    getEvents(userId,"2022-09-29");
    getEvents(userId,"2022-09-30");
    getAll(userId, "2022-09");
});

function getAll(userId="gs", targetDate= "2022-09") {
    const url = `http://133.186.211.156:8100/api/${userId}/calendars/month/${targetDate}`;
    const option = {
        method : "GET"
    };

    fetch(url, option)
        .then(function(response) {
            console.log('response', response);
            return response.json()
        }).then(function(result) {
            const todow = document.getElementById('todo-' + targetDate);

            console.log('result', result);
            console.log(todow);

            for(let i = 0; i < result.length; i++) {
                const id = result[i].id;
                const userId = result[i].userId;
                const subject = result[i].subject;
                const eventDt = result[i].eventDt;
                console.log("id",id);
                console.log("userId:",userId);
                console.log("subject:",subject);
                console.log("eventDt:",eventDt);
                
                const li = document.createElement("li");
                li.appendChild(document.createTextNode(subject));
                li.setAttribute("id",id);
                todow.appendChild(li);
            }
        });
}

function getEvents(userId, targetDate){
    /*
        promise 생성과 동시에 실행됩니다.
    */

    /*
        url에 GET으로 호출해서 
        JSON 형식의 결과를 parse해서 화면에 보여주기
    */
    const promise = new Promise(function(resolve,reject){
        const xhr = new XMLHttpRequest();
        const url = `http://133.186.211.156:8100/api/${userId}/calendars/day/${targetDate}`;

        xhr.open('GET', url);
        xhr.onreadystatechange = function () {
            if (this.readyState == 4) {
                console.log('Status: ' + this.status);
                console.log(this.responseText);
                resolve(this.responseText);
            }
        };
        xhr.send('');

    }).then(function(items){
        const result = JSON.parse(items);
        const todo = document.getElementById("todo-" + targetDate);
        for(let i=0; i<result.length; i++){
            const id = result[i].id;
            const userId = result[i].userId;
            const subject = result[i].subject;
            const eventDt = result[i].eventDt;
            console.log("id",id);
            console.log("userId:",userId);
            console.log("subject:",subject);
            console.log("eventDt:",eventDt);
            const li = document.createElement("li");
            li.appendChild(document.createTextNode(subject));
            li.setAttribute("id",id);
            li.setAttribute("onclick", 'apiDelete(' + id +')');
            todo.appendChild(li);
        }
    }).catch(e=>{
        console.log("error:",e);
    }).finally(()=>{
        console.log("api 호출");
    });
}

function saveEvent(){
    const subject=document.getElementById("subject");
    const eventDt=document.getElementById("eventDt");

    if(eventDt.value==""){
        alert("Date를 선택해 주세요!");
        eventDt.focus();
        return false;
    }else if(subject.value == ""){
        alert("제목을 입력해 주세요!");
        subject.focus();
        return false;
    }

    /*
        promise는 생성과 동시에 실행됩니다.
    */
    const promise = new Promise(function(resolve, reject){
        const xhr = new XMLHttpRequest();
        const url = `http://133.186.211.156:8100/api/${userId}/calendars/events`;
        xhr.open('POST', url);
        xhr.setRequestHeader("Content-Type",'application/json');

        const data = {
            "subject" : subject.value,
            "eventDt" : eventDt.value
        }
        
        xhr.onreadystatechange = function () {
            if (this.readyState == 4) {
                console.log('Status: ' + this.status);
                console.log(this.responseText);
                resolve(this.responseText);
            }
        };

        xhr.send(JSON.stringify(data));
        
    }).then(function(jsonString){
        result = JSON.parse(jsonString);
        console.log(result.message);
        console.log(result.id);
        alert("등록완료!");
        location.reload();
    });
}

function apiDelete(id) {

    var res = confirm('삭제하시겠습니까?');
    
    if (res == true) {
        const promies = new Promise(function(resolve, reject) {
            const xhr = new XMLHttpRequest();
            const url = `http://133.186.211.156:8100/api/${userId}/calendars/events/${id}`;
    
            xhr.open('DELETE', url);
            xhr.onreadystatechange = function () {
                if (this.readyState == 4) {
                    console.log('Status: ' + this.status);
                    console.log(this.responseText);
                    resolve(this.responseText);
                }
            };
            xhr.send('');
    
        }).finally(()=>{
            location.reload();
            console.log('apiDelete 호출');
        });
    } else {
    }    
}
