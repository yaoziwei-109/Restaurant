$(document).ready(function(){

    $("#submit").click(function () {
      var user_address=$("#address").val();
      var user_telephone=$("#telephone").val();
         var params={
               user_address : user_address,
               user_telephone : user_telephone
        };
           $.post({
               url : "./user/resetAddressTelephone",
               contentType : "application/json",
               data : JSON.stringify(params),
               success :function(result){
                      self.location.reload();
                     alert("提交成功")
               }
          });
   });


});