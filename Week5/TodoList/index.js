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
        const temp = document.getElementById('doList'+day);
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
