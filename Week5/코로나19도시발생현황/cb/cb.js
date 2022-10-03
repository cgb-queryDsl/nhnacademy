// 서비스키
var serviceKey= 'y7AGSvvUpEGhV%2FDf%2FtoLg7cghF58l2LFnUN27ezB180vPJx8Bv5giFxrVH%2B6rka2gcfUN9k4jbKOTL0lqMUR2w%3D%3D';

window.addEventListener('DOMContentLoaded', function() {
    createTable();
})

function createTable() {
    const div = document.getElementById('middle');
    const table = document.createElement('table');

    div.appendChild(table);

    const thead = document.createElement('thead');
    table.appendChild(thead);

    const tr1 = document.createElement('tr');
    thead.appendChild(tr1);
    const th1 = document.createElement('th');
    th1.appendChild(document.createTextNode('날짜'));
    const th2 = document.createElement('th');
    th2.appendChild(document.createTextNode('지역발생'));
    const th3 = document.createElement('th');
    th3.appendChild(document.createTextNode('해외유입'));
    const th4 = document.createElement('th');
    th4.appendChild(document.createTextNode('합계'));
    
    tr1.appendChild(th1);
    tr1.appendChild(th2);
    tr1.appendChild(th3);
    tr1.appendChild(th4);

    const tbody = document.createElement('tbody');
    table.appendChild(tbody);

    const promise = new Promise(function(resolve, reject) {
        var xhr = new XMLHttpRequest();
        var url = 'http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson';

        var queryParams = '?' + encodeURIComponent('serviceKey') + '='+ serviceKey; 

        queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1');
        queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10'); 
        queryParams += '&' + encodeURIComponent('startCreateDt') + '=' + encodeURIComponent('20220901'); 
        queryParams += '&' + encodeURIComponent('endCreateDt') + '=' + encodeURIComponent('20220930'); 
        xhr.open('GET', url + queryParams);

        console.log(url + queryParams);

        xhr.onreadystatechange = function() {
            if (this.readyState == 4) {
                console.log('status : ', this.status);
                resolve(this.responseText);
            }
        }
        xhr.send('');

    }).then(function(items) {
        console.log('then통과');
        
        var parser = new DOMParser();
        var xmlDoc = parser.parseFromString(items, "text/html");

        // console.log(xmlDoc);

        var result = xmlDoc.getElementsByTagName('item');
        // console.log(result);

        for(i = 0; i < result.length; i++) {
            if (result[i].getElementsByTagName('gubun')[0].textContent == '충북') {
                const createDt = result[i].getElementsByTagName('createDt')[0].textContent;
                const localOccCnt = result[i].getElementsByTagName('localOccCnt')[0].textContent;
                const overFlowCnt = result[i].getElementsByTagName('overFlowCnt')[0].textContent;

                console.log(createDt.substring(5,10), localOccCnt, overFlowCnt);

                const tr2 = document.createElement('tr');
                tbody.appendChild(tr2);

                const day = createDt.substring(5,10);
                const td1 = document.createElement('td');
                td1.appendChild(document.createTextNode(day));
                const td2 = document.createElement('td');
                td2.appendChild(document.createTextNode(localOccCnt));
                const td3 = document.createElement('td');
                td3.appendChild(document.createTextNode(overFlowCnt));

                const sum = parseInt(localOccCnt) + parseInt(overFlowCnt);
                const td4 = document.createElement('td');
                td4.appendChild(document.createTextNode(sum));

                tr2.appendChild(td1);
                tr2.appendChild(td2);
                tr2.appendChild(td3);
                tr2.appendChild(td4);
            }
        }
    });

}