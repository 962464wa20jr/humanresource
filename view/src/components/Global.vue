<script>
import Vue from 'vue'
let httpRequestUrl = null

if(process.env.NODE_ENV == 'development') {
    httpRequestUrl = 'http://localhost:8088/humanresource'
}else if(process.env.NODE_ENV == 'debug') {
    httpRequestUrl = 'http://localhost:8088/humanresource'
}else if(process.env.NODE_ENV == 'production') {
}
const showFileUrl = httpRequestUrl + 'file/showFile?path=';
const Event = new Vue();
export default {
    httpRequestUrl,
    showFileUrl,
    Event
    
}
</script>