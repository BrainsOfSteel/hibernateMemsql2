package com.hibernate.memsql.memsql.controller;

import com.hibernate.memsql.memsql.dao.CommonDaoOperation;
import com.hibernate.memsql.memsql.dao.DevicesOperationDao;
import com.hibernate.memsql.memsql.dao.UserOperationDao;
import com.hibernate.memsql.memsql.model.Devices;
import com.hibernate.memsql.memsql.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/demo")
public class MainController {

    @Autowired
    private UserOperationDao userOperationDao;

    @Autowired
    private DevicesOperationDao devicesOperationDao;

    @Autowired
    private CommonDaoOperation commonDaoOperation;

    @PostMapping(path="/add")
    @ResponseBody
    public String addNewUser(@RequestParam Integer id, @RequestParam String name,
                             @RequestParam String email) {
        return userOperationDao.saveUser(id,name,email);
    }

    @PostMapping(path="/update")
    @ResponseBody
    public String updateExitingUser(@RequestParam String name, @RequestParam String newName) {
        return userOperationDao.updateUser(name,newName);
    }

    @PostMapping(path="/add/device/user")
    @ResponseBody
    public String addNewUser(@RequestParam Integer userId, @RequestParam String name,
                             @RequestParam String email,@RequestParam Integer deviceId,
                             @RequestParam String modelNumber, @RequestParam String deviceName) {
        return commonDaoOperation.addUserAndDevice(name, email, userId, deviceId, modelNumber, deviceName);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userOperationDao.getAllUsers();
    }

    @GetMapping(path="/devices/all")
    public @ResponseBody Iterable<Devices> getAllDevices() {
        return devicesOperationDao.getAllDevices();
    }
}
