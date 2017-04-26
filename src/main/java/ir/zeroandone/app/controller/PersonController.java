package ir.zeroandone.app.controller;

import ir.zeroandone.app.application.address.dto.AddressDto;
import ir.zeroandone.app.application.address.service.AddressService;
import ir.zeroandone.app.application.sms.service.SmsService;
import ir.zeroandone.app.domain.Attachment;
import ir.zeroandone.app.domain.AttachmentRepository;
import ir.zeroandone.app.domain.Person;
import ir.zeroandone.app.domain.PersonRepository;
import ir.zeroandone.app.infra.helper.RandomString;
import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/persons")
public class PersonController extends WebMvcConfigurerAdapter {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private AttachmentRepository attachmentRepo;

    @Autowired
    private SmsService smsService;

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/faq", method = RequestMethod.GET)
    public String getFaq() {
        return "persons/faq";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPerson(Person person) {
        return "persons/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @RequestParam("files") MultipartFile[] uploadfiles) {
        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        RandomString randomString = new RandomString(8);
        person.setFollowingCode(randomString.nextString());
        if (bindingResult.hasErrors()) {
            return "persons/new";
        }
        List<Attachment> attachments = new ArrayList<>();
        Arrays.asList(uploadfiles).forEach(multipartFile -> {
                    try {
                        attachments.add(new Attachment(multipartFile.getSize(), multipartFile.getContentType(),
                                multipartFile.getOriginalFilename(), multipartFile.getName(),
                                "", "", multipartFile.getBytes()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        attachments.get(0).setTitle("Personal");
        attachments.get(1).setTitle("NationalCard");
        person.setAttachments(attachments);
        repository.save(person);
        String message = String.format("%s \n %s : %s", "اطلاعات شما با موفقیت ثبت شد.", "کد رهگیری شما", person.getFollowingCode());
        smsService.sendBySoap(message, person.getCellPhone());
        return "persons/results";
    }

    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public @ResponseBody
    List<String> getAddress(@RequestParam(value = "name", required = false, defaultValue = "Stranger") String name) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("name", name);
        return addressService.getAddress(params);
    }

    @RequestMapping(value = "v2/address", method = RequestMethod.GET)
    public @ResponseBody
    List<AddressDto> getListAddress(@RequestParam(value = "name", required = false, defaultValue = "Stranger") String name) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("name", name);
        return addressService.getListAddress(params);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPersons(Model model) {
        model.addAttribute("persons", repository.findAll());
        return "persons/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @RequestParam("files") MultipartFile[] uploadfiles) {
        long id = person.getId();
        Person person1 = repository.findOne(id);
        repository.save(person);
        return new ModelAndView("redirect:/persons/list");
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable long id,
                       Model model) {
        Person person = repository.findOne(id);
        model.addAttribute("person", person);
        return "persons/edit";
    }

    @ResponseBody
    @RequestMapping(value = "/attachments/{userId}/{title}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> downloadUserAvatarImage(@PathVariable Long userId, @PathVariable String title) {
        Attachment attachment = repository.findOne(userId).getAttachments().stream().filter(att ->
                att.getTitle().equalsIgnoreCase(title)).findFirst().orElse(null);
        if (attachment == null)
            return ResponseEntity.noContent().build();
        else
        return new ResponseEntity(attachment.getContent(), HttpStatus.ACCEPTED);
    }

//    @PostMapping("/api/upload/multi/model")
//    public ResponseEntity<?> multiUploadFileModel(@ModelAttribute UploadModel model) {
//
//        logger.debug("Multiple file upload! With UploadModel");
//
//        try {
//
//            saveUploadedFiles(Arrays.asList(model.getFiles()));
//
//        } catch (IOException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);
//
//    }
//
//    //save file
//    private void saveUploadedFiles(List<MultipartFile> files) throws IOException {
//
//        for (MultipartFile file : files) {
//
//            if (file.isEmpty()) {
//                continue; //next pls
//            }
//
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
//            Files.write(path, bytes);
//
//        }
//
//    }

    /*@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("persons/results").setViewName("results");
    }*/



/*    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable long id) {
        repository.delete(id);
        return new ModelAndView("redirect:/persons");
    }*/



 /*   @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@RequestParam("person_id") long id,
                               @RequestParam("firstName") String firstName) {
        Person person = repository.findOne(id);
        person.setFirstName(firstName);
        repository.save(person);
        return new ModelAndView("redirect:/persons");
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable long id,
                       Model model) {
        Person person = repository.findOne(id);
        model.addAttribute("person", person);
        return "persons/edit";
    }*/
}
