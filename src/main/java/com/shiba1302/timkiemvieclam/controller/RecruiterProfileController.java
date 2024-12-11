package com.shiba1302.timkiemvieclam.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shiba1302.timkiemvieclam.entity.RecruiterProfile;
import com.shiba1302.timkiemvieclam.entity.Users;
import com.shiba1302.timkiemvieclam.respository.UsersRespository;
import com.shiba1302.timkiemvieclam.services.RecuiterProfileService;
import com.shiba1302.timkiemvieclam.util.fileUploadUlti;

@Controller
@RequestMapping("/recruiter-profile")
public class RecruiterProfileController {

    private final UsersRespository usersRespository;
    private final RecuiterProfileService recuiterProfileService;

    public RecruiterProfileController(UsersRespository usersRespository,
            RecuiterProfileService recuiterProfileService) {
        this.usersRespository = usersRespository;
        this.recuiterProfileService = recuiterProfileService;
    }

    @GetMapping("/")
    public String recruiterProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            Users users = usersRespository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("khong tim thay"));
            Optional<RecruiterProfile> recruiterProfile = recuiterProfileService.getOne(users.getUserId());
            if (!recruiterProfile.isEmpty()) {
                model.addAttribute("profile", recruiterProfile.get());
            }
        }
        return "recuiter-profile";
    }

    @PostMapping("/addNew")
    public String addNewRecruiter(RecruiterProfile recruiterProfile, @RequestParam("image") MultipartFile multipartFile,
            Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            Users users = usersRespository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("khong tim thay"));
            recruiterProfile.setUserId(users);
            recruiterProfile.setUserAccountId(users.getUserId());

        }
        model.addAttribute("profile", recruiterProfile);
        String fileName = "";
        if (!multipartFile.getOriginalFilename().equals("")) {
            fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        }
        RecruiterProfile saveUser = recuiterProfileService.addNew(recruiterProfile);
        String uploadDir = "photos/recruiter/" + saveUser.getUserAccountId();
        try {
            fileUploadUlti.saveFile(uploadDir, fileName, multipartFile);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return "redirect:/dashboard-main/";
    }

}
