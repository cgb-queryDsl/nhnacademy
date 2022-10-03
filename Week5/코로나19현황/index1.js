// 서비스키
var serviceKey = 'y7AGSvvUpEGhV%2FDf%2FtoLg7cghF58l2LFnUN27ezB180vPJx8Bv5giFxrVH%2B6rka2gcfUN9k4jbKOTL0lqMUR2w%3D%3D';

var today = new Date();
// console.log(startDate.getFullYear()); // 년
// console.log(startDate.getMonth());  // 월
// console.log(startDate.getDate());   // 일
// var startYear = startDate.getFullYear();
// var startMonth = (startDate.getMonth())+1;

window.addEventListener('DOMContentLoaded', function() {
    createTableChart(today.getFullYear(), today.getMonth()+1);
})

function goPrev() {
    today = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate());
    createTableChart(today.getFullYear(), today.getMonth()+1);
}

function goNext() {
    var temp = new Date();
    
    today = new Date(today.getFullYear(), today.getMonth() + 1, today.getDate());
    createTableChart(today.getFullYear(), today.getMonth()+1);
}

// 년도와 월가지고 table생성
function createTableChart(year, month) {

    // 테이블 Top 부분 생성
    
    if (month < 10) {
        month = '0' + month;
    }

    console.log(year, month);

    const div = document.getElementById('middle');
    const table = document.createElement('table');
    table.className = 'table-covid';
    table.id = 'table-' + year + '' + month;

    // <div id="middle">
    //      <table> </table>
    // </div>
    div.appendChild(table);

    const thead = document.createElement('thead');
    thead.id = 'table-title';
    // <div id="middle">
    //      <table>
    //          <thead> </thead>
    //      </table>
    // </div>
    table.appendChild(thead);

    const tr = document.createElement('tr');
    thead.appendChild(tr);
    const th1 = document.createElement('th');
    th1.appendChild(document.createTextNode('기준일'));
    const th2 = document.createElement('th');
    th2.appendChild(document.createTextNode('누적(확진자수)'));
    const th3 = document.createElement('th');
    th3.appendChild(document.createTextNode('누적(사망자수)'));
    const th4 = document.createElement('th');
    th4.appendChild(document.createTextNode('Update'));

    tr.appendChild(th1);
    tr.appendChild(th2);
    tr.appendChild(th3);
    tr.appendChild(th4);

    // api를 통해 데이터를 받고 테이블에 추가
    const tbody = document.createElement('tbody');
    tbody.id = 'table-body';
    table.appendChild(tbody);

    let xLabel = [];
    let dataSum = [];
    let dataDeathSum = [];

    const promise = new Promise(function(resolve, reject) {
        var xhr = new XMLHttpRequest();
        var url = 'http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson'; /*URL*/

        // 파라미터로 넘어온 년, 월을 가지고 쿼리문에 추가
        var startCreateDt = year + '' + month + '01';

        month = parseInt(month);
        
        var temp = new Date(year, month, 0);
        var nextDay = temp.getDate();

        month = month.toString();
        if (month.length < 2) {
            month = '0' + month;
        }
        var endCreateDt = year + '' + month + '' + nextDay;

        // console.log(startCreateDt);
        // console.log(endCreateDt);

        var queryParams = '?' + encodeURIComponent('serviceKey') + '='+ serviceKey; /*Service Key*/
        queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /**/
        queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10'); /**/
        queryParams += '&' + encodeURIComponent('startCreateDt') + '=' + encodeURIComponent(startCreateDt); /**/
        queryParams += '&' + encodeURIComponent('endCreateDt') + '=' + encodeURIComponent(endCreateDt); /**/
        xhr.open('GET', url + queryParams);

        xhr.onreadystatechange = function() {
            if (this.readyState == 4) {
                console.log('status : ', this.status);
                // confirm.log(this.responseText);
                resolve(this.responseText);
            }
        }
        xhr.send('');

    }).then(function(items) {
        // items에 xml파일로 한 달 데이터가 들어가있음

        var parser = new DOMParser();
        var xmlDoc = parser.parseFromString(items, "text/html");
        // console.log(xmlDoc);

        var result = xmlDoc.getElementsByTagName('item');

        // console.log(result);

        for(i = 0; i < result.length; i++) {
            const createDt = result[i].getElementsByTagName('createDt')[0].textContent;
            const decideCnt = result[i].getElementsByTagName('decideCnt')[0].textContent;
            const deathCnt = result[i].getElementsByTagName('deathCnt')[0].textContent;
            const update = result[i].getElementsByTagName('updateDt')[0].textContent;

            // console.log(createDt, decideCnt, deathCnt, update);
            const day = createDt.split(' ')[0];
            xLabel.unshift(day);
            dataSum.unshift(decideCnt);
            dataDeathSum.unshift(deathCnt);

            const tr1 = document.createElement('tr');
            tbody.appendChild(tr1);

            const id = createDt.split(' ')[0];
            
            const td1 = document.createElement('td');
            td1.setAttribute('id', id);
            td1.appendChild(document.createTextNode(createDt));
            const td2 = document.createElement('td');
            td2.setAttribute('id', id + '-Sum');
            td2.appendChild(document.createTextNode(decideCnt));
            const td3 = document.createElement('td');
            td3.setAttribute('id', id + '-deathSum');
            td3.appendChild(document.createTextNode(deathCnt));
            const td4 = document.createElement('td');
            td4.appendChild(document.createTextNode(update));

            tr1.appendChild(td1);
            tr1.appendChild(td2);
            tr1.appendChild(td3);
            tr1.appendChild(td4);
        }

    });

    // console.log(xLabel, dataSum, dataDeathSum);
    
    new Chart(document.getElementById("line-chart"), {
        type: 'line',
        data: {
            labels: xLabel,
            datasets: [
                { 
                    data: dataSum,
                    label: "확진자(누적)",
                    borderColor: "#FF69B4",
                    fill: false
                }, 
                { 
                    data: dataDeathSum,
                    label: "사망자(누적)",
                    borderColor: "#6495ED",
                    fill: false
                }, 
            ]
            },
    });
}


// 이전달 다음달 클릭하면 출려된 데이터를 없애기