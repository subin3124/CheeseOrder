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
}
