$(document).ready(function(){

    $(".container-fluid button").click(function () {
       var t = $(this);
       var id=$(this).attr("id");
       var params={
           food_id : id,
        };
         $.post({
        url : "./order/updateFoodState",
        contentType : "application/json",
        data : JSON.stringify(params),
        success : function(result){
                alert("成功添加");
                 t.parent().parent().parent().remove();
            }
         });
    });

    $(".container-fluid button").each(function( index ) {
            var t = $(this);
            var id = $(this).attr("id");
              console.log("index"+id);
            var params={
             food_id : id,
            };
            $.post({
               url : "./food/getFoodByFoodId",
               contentType : "application/json",
               data : JSON.stringify(params),
                async: false,
               success : function(result){
                   console.log(result);
                  t.parent().find("span").text(result.food_number);
                }
            });

            $.post({
                url : "./order/getFoodState",
                contentType : "application/json",
                data : JSON.stringify(params),
                success : function(result){
                      if(result==-1) //存在cache里面
                      t.parent().parent().parent().remove();
                  }
          });
    });

});