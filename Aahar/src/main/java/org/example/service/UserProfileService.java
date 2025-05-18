package org.example.service;

import org.example.dto.*;
import org.example.entity.*;

public interface UserProfileService {
    UserProfile createUser(UserProfileRequestDTO dto);
}

