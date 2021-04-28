/* Javascript 샘플 코드 */

console.log(HOUSE_SALES_APIKEY);
var xhr = new XMLHttpRequest();
var url = 'http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcRHTrade'; /*URL*/
var queryParams = '?' + encodeURIComponent('ServiceKey') + '='+ HOUSE_SALES_APIKEY; /*Service Key*/
queryParams += '&' + encodeURIComponent('LAWD_CD') + '=' + encodeURIComponent('11110'); /**/
queryParams += '&' + encodeURIComponent('DEAL_YMD') + '=' + encodeURIComponent('202104'); /**/
xhr.open('GET', url + queryParams);
xhr.onreadystatechange = function () {
    if (this.readyState == 4) {
        alert('Status: '+this.status+'nHeaders: '+JSON.stringify(this.getAllResponseHeaders())+'nBody: '+this.responseText);
    }
};

xhr.send('');