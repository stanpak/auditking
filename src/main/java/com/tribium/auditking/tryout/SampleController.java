package com.tribium.auditking.tryout;

import com.tribium.auditking.core.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    @Autowired SampleService sampleService;

    @GetMapping("/doNothing")
    public void doNothing(){}

    @GetMapping("/audited")
    public MyObject audited(){
        MyObject obj = new MyObject();
        obj.string = "val";
        obj.integer = 1;
        return sampleService.auditedMethod(obj);
    }

    @GetMapping("/direct")
    @Audited
    public MyObject direct(){
        MyObject obj = new MyObject();
        obj.string = "val";
        obj.integer = 1;
        return obj;
    }

    public static class MyObject{
        public String string;
        public Integer integer;

        @Override
        public String toString() {
            return "MyObject{" +
                    "string='" + string + '\'' +
                    ", integer=" + integer +
                    '}';
        }
    }
}
