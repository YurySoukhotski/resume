hotelApp.filter('truefalse', function(){
        return function(text){
          return text ? "Present" : "Not";
        }
      })