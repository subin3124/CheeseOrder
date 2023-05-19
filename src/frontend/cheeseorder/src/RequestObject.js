import DragFromOutsideLayout from "./DragFromOutsideLayout";

export default class RequestObject{
static data = new Array();
   async getTableList(shopId,floor) {
        shopId="0001";
        floor="1";
        let f=await fetch("http://localhost:8080/Admin/"+shopId+"/"+floor,{
            headers : {
                "content-type" : "application/json"
            },
            method : "get",
        });
        let json = await f.json();
        RequestObject.data = json;
        console.log(json);
    }
    async moveTable(tableId,x,y,h,w) {
       let tableSize = {
           x: x,
           y:y,
           weight:w,
           height:h,
       }
       console.log(tableSize);
        let f = await fetch("http://localhost:8080/Admin/Move/"+tableId,{
           headers : {
               "content-type" : "application/json"
           },
            method:"post",
            body:JSON.stringify(tableSize),
        });
       let json = await f.json();
       if(json.code!=200) {
           alert("오류코드 : "+json.code+"\n"+json.message);
       }
       console.log("성공?? movetable");
    }
    async createTable(TableInfo) {
        let f = await fetch("http://localhost:8080/Admin/Create/"+TableInfo.tableid,{
           headers:{
               "content-type" : "application/json",
           },
            method : "post",
            body:JSON.stringify(TableInfo),
        });
        let json = await f.json();
        if(json.code!=200) {
            alert("오류코드 : "+json.code+"\n"+json.message);
        }
    }
}
