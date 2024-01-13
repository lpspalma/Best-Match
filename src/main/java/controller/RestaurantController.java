package controller;

import com.bestmatch.BestMatch.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

   /* @PostMapping("/read")
    ResponseEntity<HttpStatus> readRestaurantFromCSV(@RequestBody String path){
        return restaurantService.readRestaurantFromCSV(path) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/
}
