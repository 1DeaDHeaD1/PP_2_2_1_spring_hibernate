package hiber.service;

import hiber.model.User;

public interface CarUserService {

    User getUserForCarDetails(String model, int series);

}
