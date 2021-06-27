$(document).ready(function(){

   $.get({
          url : "./order/getAllOrder",
          success :function(result){
                console.log("my_order"+result);
                $(result).each(function( index ) {
                     var order_id= this.order_id;
                     var order_prices= this.order_prices;
                     var params={
                             order_id : order_id
                      };
                      //获取order的详细信息
                      $.post({
                       url : "./order/getItemsByOrderId",
                       contentType : "application/json",
                       data : JSON.stringify(params),
                       success :function(result){
                            //创建单个order

                         $("#parent").prepend(function() {
                                return '<div  class="col-lg-6 card border-left-primary  m-3">'+
                                        '<div class="row">'+
                                        '<font size="5" color="black" class="m-3">'+"总价格:"+'<span>'+order_prices+'</span>'+'</font>'+
                                        '<div size="3" style="margin-left:auto;margin-right:2px;margin-top:auto;margin-bottom:0px" >'+"订单号:"+'<span>'+order_id+'</span>'+'</div>'+
                                         '</div>'+
                                        '</div>'
                          });
                           $("#parent div:first").attr("id",order_id);
                             //给单个order添加商品
                             $(result).each(function( index ) {
                               var order_item_food_number=this.order_item_food_number;
                               var food_id=this.food_id;
                               var params={
                                    food_id : food_id
                                 };
                               $.post({
                                   url : "./food/getFoodByFoodId",
                                   contentType : "application/json",
                                    data : JSON.stringify(params),
                                    async: false,
                                      success :function(result){
                                        var food_price=result.food_price;
                                        var food_name=result.food_name;
                                         $("#parent div:first").prepend(function() {
                                          return  '<div  class="row m-3" >'+
                                                  '<img  class="aligncenter ">'+
                                      '            <div  class=" text-primary text-uppercase ml-3">'+food_name+'</div>'+
                                                  '<div   class=" ml-2" >'+"价格:"+'<span>'+food_price+'</span>'+'</div>'+
                                                  '<div   class=" ml-2" >'+"数量:"+'<span>'+order_item_food_number+'</span>'+'</div>'+
                                                  '</div>';
                                           });
                                         $("#parent div:first img:first").attr("src","/food/"+food_id+".jpg");
                                      }

                                });
                               });
                             }

                        });  //post getItemsByOrderId
                     }); //each

                  } //get success
           }); //get


});  //ready