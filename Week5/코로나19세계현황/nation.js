var serviceKey = 'y7AGSvvUpEGhV%2FDf%2FtoLg7cghF58l2LFnUN27ezB180vPJx8Bv5giFxrVH%2B6rka2gcfUN9k4jbKOTL0lqMUR2w%3D%3D';
var nat = document.title;

window.addEventListener('DOMContentLoaded', function() {
    create(nat);
});


function create(nation) {
    const title = document.getElementById('title');
    title.innerHTML = nation + ' 코로나 현황';

    // 테이블 구성
    // =================================================
    const div = document.getElementById('middle');
    const table = document.createElement('table');
    table.id = 'covid';

    div.appendChild(table);
    
    const thead = document.createElement('thead');
    table.appendChild(thead);

    const tr1 = document.createElement('tr');
    thead.appendChild(tr1);
    
    const th1 = document.createElement('th');
    th1.appendChild(document.createTextNode('날짜'));
    const th2 = document.createElement('th');
    th2.appendChild(document.createTextNode('확진자수'))

    tr1.appendChild(th1);
    tr1.appendChild(th2);
    // 테이블 구성 끝
    // ======================================================

    const tbody = document.createElement('tbody');
    table.appendChild(tbody);

    const promise = new Promise(function(resolve, reject) {
        var xhr = new XMLHttpRequest();
        var url = 'http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19NatInfStateJson'; /*URL*/
        var queryParams = '?' + encodeURIComponent('serviceKey') + '='+ serviceKey; /*Service Key*/
        queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /**/
        queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10'); /**/
        queryParams += '&' + encodeURIComponent('startCreateDt') + '=' + encodeURIComponent('20220901'); /**/
        queryParams += '&' + encodeURIComponent('endCreateDt') + '=' + encodeURIComponent('20220930'); /**/
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
        var parser = new DOMParser();
        var xmlDoc = parser.parseFromString(items, "text/html");

        var result = xmlDoc.getElementsByTagName("item");

        for(i = 0; i < result.length; i++) {
            var res_nationNmEn = result[i].getElementsByTagName('nationNmEn')[0].textContent;

            if (res_nationNmEn == nation) {
                const tr2 = document.createElement('tr');
                tbody.appendChild(tr2);
                const createDt = result[i].getElementsByTagName('createDt')[0].textContent;
                const natDefCnt = result[i].getElementsByTagName('natDefCnt')[0].textContent;

                var day = createDt.split(' ')[0];
                const td1 = document.createElement('td');
                td1.appendChild(document.createTextNode(day));
                const td2 = document.createElement('td');
                td2.appendChild(document.createTextNode(natDefCnt));

                tr2.appendChild(td1);
                tr2.appendChild(td2);
            }

        }
    });

}
