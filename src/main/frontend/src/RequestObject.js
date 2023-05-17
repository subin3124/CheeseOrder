export default class RequestObject{

    getTableList(shopId,floor) {
        fetch("http://localhost:8080/Admin/Create/"+shopId+"/"+floor,{
            headers : {
                "content-type" : "application/json"
            },
            method : "get",
        }).then((value) => {value.json().then((json) => {

        })});

    }
}