package com.tlp.mrhill.service;

import com.tlp.mrhill.model.User;
import com.tlp.mrhill.vo.JsonRequest;

import java.util.List;

public interface Login {
   User longin(User user);
   JsonRequest findAllUser(int pageNum, int pageSize);
}
