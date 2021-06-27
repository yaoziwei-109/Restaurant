$(document).ready(function(){
     var prices = 0;
     var a =[];
       jQuery.extend({
            'updatePrices':function(){
               prices=0;
               $("#parent  span").each(function( index ) {
                  a[index]=$(this).text();
                });
                $("#parent  input").each(function( index ) {
                      prices+=a[index]*($(this).val())
                });
               $("#prices").text(prices);
           }
        })

      $.get({
        url : "./order/getAllCacheOrderItem",
        success :function(result){
              $(result).each(function( index ) {
                   var foodid= this.food_id;
                   var item_food_number= this.order_item_food_number;
                   var params={
                           food_id : foodid
                    };
                    //获取food的详细信息
                    $.post({
                     url : "./food/getFoodByFoodId",
                     contentType : "application/json",
                     data : JSON.stringify(params),
                     success :function(result){
                           var food_price=result.food_price;
                           var food_name=result.food_name;
                           $("#parent").prepend(function() {
                             return '<div  class="col-md-4 card border-left-primary  m-3">'+
                              '<div  class="row" >'+
                              '<div  class="col ml-2" >'+
                              '<div  class=" text-primary text-uppercase mt-3 mb-4">'+food_name+'</div>'+
                              '<div   class=" mb-4" >'+"价格:"+'<span>'+food_price+'</span>'+'</div>'+
                              '<input   class="form-control mb-4" placeholder="数量">'+'</input>'+
                              '</div>'+
                              '<img  class="aligncenter ">'+
                              '<button  class=" btn mbutton btn-danger btn-block "><i class="fas fa-trash"></i></button>'+
                              '</div>'+
                              '</div>';
                           });
                           //更新取消按钮的ID
                            $("#parent div:first button").attr("id",foodid);
                           //更新订单项原始数目并记录prices
                            $("#parent div:first input").val(item_food_number);
                            //更新图片
                            $("#parent div:first img").attr("src","/food/"+foodid+".jpg");
                            prices+=food_price*item_food_number;
                            $("#prices").text(prices);

                      }
                    });
                     console.log( index + ":" + this.food_id);
                    console.log( index + ":" + this.order_item_food_number);
                    console.log( index + ":" + this.user_name);
              });
             }
         });

        //输入时更新food金额
       $("#parent").on("input propertychange change","input",function () {
           $.updatePrices();
          var number = $(this).val();
          var id =  $(this).parent().next().next().attr("id");
           console.log("a"+number);
           console.log("b"+id);
           var params={
                    food_id :  id,
                    food_number : number,
             };
                  $.post({
                 url : "./order/updateOrderItemNumber",
                contentType : "application/json",
                 data : JSON.stringify(params),
               success : function(result){
               }
          });
       });

         //取消具体food
        $("#parent ").on("click","button",function () {
            var id = $(this).attr("id");
             console.log("id"+id);
             var params={
                       food_id : id,
                    };
                     $.post({
                    url : "./order/deleteOrderItemByFoodId",
                    contentType : "application/json",
                    data : JSON.stringify(params),
                    success : function(result){

                        }
                     });
            $(this).parent().parent().remove();
            $.updatePrices();
         });

        //提交订单
        $("#submit").on("click",function () {
           var params={
                 food_prices : $("#prices").text(),
              };
              $.post({
                     url : "./order/createOrder",
                     contentType : "application/json",
                     data : JSON.stringify(params),
                     success : function(result){
                         if(result!=-1){
                            alert("订单创建成功");
                                 $("#parent >div").each(function( index ) {
                                 $(this).remove();
                              });
                             $("#prices").text("0");
                         }else{
                             alert("订单创建失败");
                         }

                      }
               });
       });

 });