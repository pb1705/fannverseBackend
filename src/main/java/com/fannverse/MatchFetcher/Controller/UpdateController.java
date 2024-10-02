package com.fannverse.MatchFetcher.Controller;


import com.fannverse.MatchFetcher.Service.UpdateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/update")
public class UpdateController {

    @Autowired
    UpdateHandler updateHandler;

    @GetMapping
    public void runUpdate() {
        updateHandler.updateRedis();
    }
}
