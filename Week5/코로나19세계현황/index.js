var serviceKey = 'y7AGSvvUpEGhV%2FDf%2FtoLg7cghF58l2LFnUN27ezB180vPJx8Bv5giFxrVH%2B6rka2gcfUN9k4jbKOTL0lqMUR2w%3D%3D';

window.addEventListener('DOMContentLoaded', function(){
    create();
});


function create() {
    // 테이블 구조 잡기 시작
    // ========================================================================
    const divAfrica = document.getElementById('africa');            // 아프리카 테이블
    const divOceania =document.getElementById('oceania');           // 오세아니아 테이블
    const divEurope = document.getElementById('europe');            // 유럽 테이블
    const divAmerica = document.getElementById('america');          // 아메리카 테이블  
    const divMiddleEast = document.getElementById('middleEast');    // 중동 테이블
    const divAsia = document.getElementById('asia');                // 아시아 테이블
    const divOther = document.getElementById('other');              // 기타 테이블

    // table
    // 1번 : 아프리카
    // 2번 : 오세아니아
    // 3번 : 유럽
    // 4번 : 아메리카
    // 5번 : 중동
    // 6번 : 아시아
    // 7번 : 기타
    const table1 = document.createElement('table');                 
    const table2 = document.createElement('table');
    const table3 = document.createElement('table');
    const table4 = document.createElement('table');
    const table5 = document.createElement('table');
    const table6 = document.createElement('table');
    const table7 = document.createElement('table');

    divAfrica.appendChild(table1);
    divOceania.appendChild(table2);
    divEurope.appendChild(table3);
    divAmerica.appendChild(table4);
    divMiddleEast.appendChild(table5);
    divAsia.appendChild(table6);
    divOther.appendChild(table7);

    // caption
    // 1번 : 아프리카
    // 2번 : 오세아니아
    // 3번 : 유럽
    // 4번 : 아메리카
    // 5번 : 중동
    // 6번 : 아시아
    // 7번 : 기타
    const caption1 = document.createElement('caption');
    const caption2 = document.createElement('caption');
    const caption3 = document.createElement('caption');
    const caption4 = document.createElement('caption');
    const caption5 = document.createElement('caption');
    const caption6 = document.createElement('caption');
    const caption7 = document.createElement('caption');

    caption1.appendChild(document.createTextNode('아프리카'));
    caption2.appendChild(document.createTextNode('오세아니아'));
    caption3.appendChild(document.createTextNode('유럽'));
    caption4.appendChild(document.createTextNode('아메리카'));
    caption5.appendChild(document.createTextNode('중동'));
    caption6.appendChild(document.createTextNode('아시아'));
    caption7.appendChild(document.createTextNode('기타'));
    
    table1.appendChild(caption1);
    table2.appendChild(caption2);
    table3.appendChild(caption3);
    table4.appendChild(caption4);
    table5.appendChild(caption5);
    table6.appendChild(caption6);
    table7.appendChild(caption7);

    // thead
    // 1번 : 아프리카
    // 2번 : 오세아니아
    // 3번 : 유럽
    // 4번 : 아메리카
    // 5번 : 중동
    // 6번 : 아시아
    // 7번 : 기타
    const thead1 = document.createElement('thead');
    const thead2 = document.createElement('thead');
    const thead3 = document.createElement('thead');
    const thead4 = document.createElement('thead');
    const thead5 = document.createElement('thead');
    const thead6 = document.createElement('thead');
    const thead7 = document.createElement('thead');

    table1.appendChild(thead1);
    table2.appendChild(thead2);
    table3.appendChild(thead3);
    table4.appendChild(thead4);
    table5.appendChild(thead5);
    table6.appendChild(thead6);
    table7.appendChild(thead7);

    // tr
    // 1번 : 아프리카
    // 2번 : 오세아니아
    // 3번 : 유럽
    // 4번 : 아메리카
    // 5번 : 중동
    // 6번 : 아시아
    // 7번 : 기타
    const tr1 = document.createElement('tr');
    const tr2 = document.createElement('tr');
    const tr3 = document.createElement('tr');
    const tr4 = document.createElement('tr');
    const tr5 = document.createElement('tr');
    const tr6 = document.createElement('tr');
    const tr7 = document.createElement('tr');

    thead1.appendChild(tr1);
    thead2.appendChild(tr2);
    thead3.appendChild(tr3);
    thead4.appendChild(tr4);
    thead5.appendChild(tr5);
    thead6.appendChild(tr6);
    thead7.appendChild(tr7);

    // th*2
    // 1번-2 : 아프리카
    // 3번-4 : 오세아니아
    // 5번-6 : 유럽
    // 7번-8 : 아메리카
    // 9번-10 : 중동
    // 11번-12 : 아시아
    // 13번-14 : 기타
    // 국가 & 누적확진자 
    // 하나의 tr당 2개의 th
    const th1 = document.createElement('th');
    th1.appendChild(document.createTextNode('국가'));
    const th2 = document.createElement('th');
    th2.appendChild(document.createTextNode('누적확진자'));
    const th3 = document.createElement('th');
    th3.appendChild(document.createTextNode('국가'));
    const th4 = document.createElement('th');
    th4.appendChild(document.createTextNode('누적확진자'));
    const th5 = document.createElement('th');
    th5.appendChild(document.createTextNode('국가'));
    const th6 = document.createElement('th');
    th6.appendChild(document.createTextNode('누적확진자'));
    const th7 = document.createElement('th');
    th7.appendChild(document.createTextNode('국가'));
    const th8 = document.createElement('th');
    th8.appendChild(document.createTextNode('누적확진자'));
    const th9 = document.createElement('th');
    th9.appendChild(document.createTextNode('국가'));
    const th10 = document.createElement('th');
    th10.appendChild(document.createTextNode('누적확진자'));
    const th11 = document.createElement('th');
    th11.appendChild(document.createTextNode('국가'));
    const th12 = document.createElement('th');
    th12.appendChild(document.createTextNode('누적확진자'));
    const th13 = document.createElement('th');
    th13.appendChild(document.createTextNode('국가'));
    const th14 = document.createElement('th');
    th14.appendChild(document.createTextNode('누적확진자'));

    tr1.appendChild(th1);
    tr1.appendChild(th2);
    tr2.appendChild(th3);
    tr2.appendChild(th4);
    tr3.appendChild(th5);
    tr3.appendChild(th6);
    tr4.appendChild(th7);
    tr4.appendChild(th8);
    tr5.appendChild(th9);
    tr5.appendChild(th10);
    tr6.appendChild(th11);
    tr6.appendChild(th12);
    tr7.appendChild(th13);
    tr7.appendChild(th14);

    // tbody
    // 1번 : 아프리카
    // 2번 : 오세아니아
    // 3번 : 유럽
    // 4번 : 아메리카
    // 5번 : 중동
    // 6번 : 아시아
    // 7번 : 기타
    const tbody1 = document.createElement('tbody');
    const tbody2 = document.createElement('tbody');
    const tbody3 = document.createElement('tbody');
    const tbody4 = document.createElement('tbody');
    const tbody5 = document.createElement('tbody');
    const tbody6 = document.createElement('tbody');
    const tbody7 = document.createElement('tbody');

    table1.appendChild(tbody1);
    table2.appendChild(tbody2);
    table3.appendChild(tbody3);
    table4.appendChild(tbody4);
    table5.appendChild(tbody5);
    table6.appendChild(tbody6);
    table7.appendChild(tbody7);


    // 기본 테이블 구성 끝
    // ======================================================================

    // api를 불러서 20220929날짜(제일 최신 데이터)의 natdefcnt 태그에 나라별 확진자 수를 
    // 대륙에 맞게 분류하면서 각 대륙에 추가

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
        // console.log('then 통과');

        var parser = new DOMParser();
        var xmlDoc = parser.parseFromString(items, "text/html");
        // console.log(xmlDoc);

        var result = xmlDoc.getElementsByTagName("item");
        // console.log(result);

        for(i = 0; i < result.length; i++) {
            var createDt = result[i].getElementsByTagName('createDt')[0].textContent;
            
            const day = createDt.substring(0,10);         

            // for문 돌면서 대륙별로 추가하기
            if (day == '2022-09-29') {
                const area = result[i].getElementsByTagName('areaNm')[0].textContent;

                const areaNmEn = result[i].getElementsByTagName('areaNmEn')[0].textContent;
                const nationNmEn = result[i].getElementsByTagName('nationNmEn')[0].textContent;

                const nationNm = result[i].getElementsByTagName('nationNm')[0].textContent;
                const natDefCnt = result[i].getElementsByTagName('natDefCnt')[0].textContent;

                if (area == '아프리카') {
                    const tr11 = document.createElement('tr');
                    tbody1.appendChild(tr11);

                    const td1 = document.createElement('td');
                    const td2 = document.createElement('td');

                    td1.id = 'nation';

                    td1.appendChild(document.createTextNode(nationNm));    
                    td2.appendChild(document.createTextNode(natDefCnt));
                    td1.setAttribute('onclick', "window.open('./" + areaNmEn + "/" + nationNmEn + ".html')");

                    tr11.appendChild(td1);
                    tr11.appendChild(td2);
                } else if (area == '오세아니아') {
                    const tr12 = document.createElement('tr');
                    tbody2.appendChild(tr12);  

                    const td3 = document.createElement('td');
                    const td4 = document.createElement('td');
                    
                    td3.id = 'nation';

                    td3.appendChild(document.createTextNode(nationNm));
                    td4.appendChild(document.createTextNode(natDefCnt));
                    td3.setAttribute('onclick', "window.open('./" + areaNmEn + "/" + nationNmEn + ".html')");

                    tr12.appendChild(td3);
                    tr12.appendChild(td4);
                } else if (area == '유럽') {
                    const tr13 = document.createElement('tr');
                    tbody3.appendChild(tr13);

                    const td5 = document.createElement('td');
                    const td6 = document.createElement('td');

                    td5.id = 'nation';

                    td5.appendChild(document.createTextNode(nationNm));
                    td6.appendChild(document.createTextNode(natDefCnt));
                    td5.setAttribute('onclick', "window.open('./" + areaNmEn + "/" + nationNmEn + ".html')");

                    tr13.appendChild(td5);
                    tr13.appendChild(td6);
                } else if (area == '아메리카') {
                    const tr14 = document.createElement('tr');
                    tbody4.appendChild(tr14);

                    const td7 = document.createElement('td');
                    const td8 = document.createElement('td');

                    td7.id = 'nation';

                    td7.appendChild(document.createTextNode(nationNm));
                    td8.appendChild(document.createTextNode(natDefCnt));
                    td7.setAttribute('onclick', "window.open('./" + areaNmEn + "/" + nationNmEn + ".html')");

                    tr14.appendChild(td7);
                    tr14.appendChild(td8);
                } else if (area == '중동') {
                    const tr15 = document.createElement('tr');
                    tbody5.appendChild(tr15);  
                    
                    const td9 = document.createElement('td');
                    const td10 = document.createElement('td');

                    td9.id = 'nation';

                    td9.appendChild(document.createTextNode(nationNm));
                    td10.appendChild(document.createTextNode(natDefCnt));
                    td9.setAttribute('onclick', "window.open('./" + areaNmEn + "/" + nationNmEn + ".html')");

                    tr15.appendChild(td9);
                    tr15.appendChild(td10);
                } else if (area == '아시아') {
                    const tr16 = document.createElement('tr');
                    tbody6.appendChild(tr16); 

                    const td11 = document.createElement('td');
                    const td12 = document.createElement('td');

                    td11.id = 'nation';

                    td11.appendChild(document.createTextNode(nationNm));
                    td12.appendChild(document.createTextNode(natDefCnt));
                    td11.setAttribute('onclick', "window.open('./" + areaNmEn + "/" + nationNmEn + ".html')");

                    tr16.appendChild(td11);
                    tr16.appendChild(td12);
                } else if (area == '기타') {
                    const tr17 = document.createElement('tr');
                    tbody7.appendChild(tr17);

                    const td13 = document.createElement('td');
                    const td14 = document.createElement('td');
        
                    td13.id = 'nation';

                    td13.appendChild(document.createTextNode(nationNm));
                    td14.appendChild(document.createTextNode(natDefCnt));
                    td13.setAttribute('onclick', "window.open('./" + areaNmEn + "/" + nationNmEn + ".html')");

                    tr17.appendChild(td13);
                    tr17.appendChild(td14);
                }

            } else {
                break;
            }
        }
    });

}

google.charts.load('current', {
    'packages': ['geochart'],
});
google.charts.setOnLoadCallback(drawRegionsMap);

function drawRegionsMap() {
    var data = google.visualization.arrayToDataTable([
        ['Country', 'Covid-19 Positive Count'],
        // 아프리카
        ['Lesotho', 34490],
        ['Comoros', 8471],
        ['Sao Tome and Principe', 6209],
        ['SS', 17780],
        ['Malawi', 88005],
        ['Sierra Leone', 7751],
        ['Brundi', 50129],
        ['Botswana', 326288],
        ['Mali', 32622],
        ['Guinea-Bissau', 8796],
        ['Mozambique', 230219],
        ['Uganda', 169051],
        ['Eritrea', 10168],
        ['Angola', 103131],
        ['Madagascar', 66679],
        ['Somalia', 27207],
        ['Zimbabwe', 257342],
        ['Cabo Verde', 62359],
        ['Niger', 9407],
        ['Chad', 7581],
        ['Mauritius', 261976],
        ['Zambia', 333387],
        ['Gambia', 12442],
        ['Djibouti', 15690],
        ['Tanzania', 39341],
        ['Liberia', 7961],
        ['Benin', 27638],
        ['Seychelles', 46358],
        ['Rwanda', 132488],
        ['Mauritania', 62791],
        ['Eswatini', 73390],
        ['Equatorial Guinea', 16913],
        ['CG', 24775],
        ['Central African Republic', 14912],
        ['Namibia', 166766],
        ['Kenya', 338404],
        ['Guinea', 3765],
        ['Ghana', 169100],
        ['Gabon', 48691],
        ['Ethiopia', 493510],
        ['Sudan', 63280],
        ["CI", 87159],
        ['CD', 92851],
        ['Burkina Faso', 21631],
        ['Togo', 38987],
        ['South Africa', 4018102],
        ['Cameroon', 121652],
        ['Senegal', 88373],
        ['Nigeria', 265316],
        
        // 오세아니아
        ['Samoa', 15925],
        ['Vanuatu', 11950],
        ['Solomon Islands', 21544],
        ['Marshall Islands', 15275],
        ['Papua New Guinea', 44988],
        ['Fiji', 68234],
        ['New Zealand', 1762209],
        ['Australia', 10191312],

        // 유럽
        ['RU', 20909731],
        ['XK', 272008],
        ['Montenegro', 279486],
        ['TR', 16873793],
        ['Cyprus', 585313],
        ['Albania', 330920],
        ['Moldova, Republic of', 589437],
        ['Malta', 114628],
        ['Bulgaria', 1257149],
        ['Slovakia', 1842619],
        ['Serbia', 2354273],
        ['Liechtenstein', 19644],
        ['Slovenia', 1175643],
        ['Bosnia and Herzegovina', 398506],
        ['Hungary', 2094142],
        ['Ukraine', 5133080],
        ['Poland', 6285409],
        ['Andorra', 46147],
        ['Latvia', 925883],
        ['Portugal', 5480864],
        ['Czechia', 4099713],
        ['Ireland', 1662008],
        ['Armenia', 442875],
        ['Luxembourg', 290945],
        ['Monaco', 14586],
        ['Iceland', 205637],
        ['Azerbaijan', 820998],
        ['San Marino', 20753],
        ['Lithuania', 1247792],
        ['Belarus', 994037],
        ['Netherlands', 8422336],
        ['Romania', 3264771],
        ['Norway', 1462045],
        ['North Macedonia', 342775],
        ['Greece', 4920192],
        ['Georgia', 1780691],
        ['Estonia', 601993],
        ['Denmark', 3295469],
        ['Belgium', 4533249],
        ['Switzerland', 4084315],
        ['Sweden', 2583370],
        ['Finland', 1287044],
        ['Croatia', 1229570],
        ['Austria', 5090354],
        ['Spain', 13412263],
        ['United kingdom', 23621956],
        ['France', 34200464],
        ['Germany', 33137143],
        ['Italy', 22358487],

        // 아메리카
        ['Suriname', 81099],
        ['Guyana', 71315],
        ['Venezuela', 544531],
        ['Paraguay', 716059],
        ['Uruguay', 985422],
        ['Bolivia', 1107963],
        ['Argentina', 9708420],
        ['CO', 6306552],
        ['Chile', 4612278],
        ['Ecuador', 1002057],
        ['Peru', 4141410],
        ['Brazil', 34638288],
        ['Saint Kitts and Nevis', 6543],
        ['Dominica', 14852],
        ['Saint Vincent and the Grenadines', 9448],
        ['Saint Lucia', 29408],
        ['Grenada', 19536],
        ['Antigua and Barbuda', 9089],
        ['Barbados', 102456],
        ['Bahamas', 37258],
        ['Haiti', 33702],
        ['Trinidad and Tobago', 182597],
        ['Jamaica', 151706],
        ['Cuba', 1111164],
        ['Dominican Republic', 644356],
        ['Nicaragua', 15070],
        ['Belize', 68784],
        ['El Salvador', 201785],
        ['Costa Rica', 1117989],
        ['Guatemala', 1120199],
        ['Honduras', 456391],
        ['Panama', 986866],
        ['Mexico', 7082034],
        ['Canada', 4233468],
        ['United States', 94833079],

        // 중동
        ['Yemen', 11935],
        ['Algeria', 270660],
        ['United Arab Emirates', 1026244],
        ['Israel', 4657323],
        ['Tunisia', 1145686],
        ['SY', 57284],
        ['Saudi Arabia', 816130],
        ['Qatar', 448750],
        ['Oman', 38424],
        ['Morocco', 1264903],
        ['Libya', 506988],
        ['Lebanon', 1215265],
        ['Kuwait', 658520],
        ['Jordan', 1746997],
        ['Iraq', 2459505],
        ['Egypt', 515371],
        ['Bahrain', 679560],
        ['Iran', 7547706],

        // 아시아
        ['Tajikistan', 17786],
        ["Laos", 215799],
        ['Myanmar', 622042],
        ['Timor-Leste', 23253],
        ['Kyrgyzstan', 206083],
        ['Uzbekistan', 244186],
        ['Kazakhstan', 1483411],
        ['Mongolia', 982864],
        ['Brunei Darussalam', 227756],
        ['Bangladesh', 2023145],
        ['Maldives', 185042],
        ['Bhutan', 61969],
        ['Indonesia', 6427764],
        ['Pakistan', 1572410],
        ['Afghanistan', 198750],
        ['Sri Lanka', 670732],
        ['Nepal', 999656],
        ['Cambodia', 137856],
        ['Philippines', 3941656],
        ['India', 44579088],
        ['Vietnam', 11475321],
        ['Malaysia', 4834560],
        ['Thailand', 4679833],
        ['Singapore', 1897307],
        ['Japan', 21118325],
        ['Macao', 793],
        ['Taiwan', 6373121],
        ['Hong Kong', 1753201],
        ['China', 250293],
        ['South Korea', 24746035]
    ]);

    var options = {};

    var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));

    chart.draw(data, options);
}