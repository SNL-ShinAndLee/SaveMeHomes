function getContextPath() {
    var hostIndex = location.href.indexOf( location.host ) + location.host.length;
    return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
}
const root = getContextPath();

function searchParam(key) {
	return new URLSearchParams(location.search).get(key);
}
