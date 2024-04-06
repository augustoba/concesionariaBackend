package com.proyecto.b.s.service.service_impl;


import com.proyecto.b.s.service.EmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    /*private final JavaMailSender javaMailSender;
    private final UserService userService;

    public EmailServiceImpl(JavaMailSender javaMailSender, UserService userService) {
        this.javaMailSender = javaMailSender;
        this.userService = userService;
    }*/

  /*  @Override
    public void sendPasswordResetEmail(String recipientEmail) throws ResourceNotFoundException {
        User objUser = userService.findByEmail(recipientEmail).orElseThrow(() -> new ResourceNotFoundException("noExistDB", "Usuario"));
        String formattedName = formatName(objUser);

        String randomPassword = generatePassword();
        userService.changeUserPassword(recipientEmail, randomPassword);

        String formattedMessage = "¡Hola " + formattedName + "!\n\n" +
                "Recientemente solicitaste una recuperación de contraseña en nuestra aplicación ByS.\n\n" +
                "Tu nueva contraseña temporal es: " + randomPassword + "\n\n" +
                "Te recomendamos cambiar esta contraseña temporal por una más segura después de iniciar sesión.\n\n" +
                "¡Gracias y que tengas un buen día!\n\n" +
                "Soporte ByS";

        customMessageAndSendMail(recipientEmail, "Recuperación de contraseña", formattedMessage);
    }*/

    /*@Override
    public void sendSearchModificationMail(Search objSearchBeforeModification, Search objSearch) throws ResourceNotFoundException {
        List<User> userBeforeModificationList = objSearchBeforeModification.getUserList();
        List<User> userList = objSearch.getUserList();

        HashSet<User> allUsersHashSet = new HashSet<>();
        allUsersHashSet.addAll(userBeforeModificationList);
        allUsersHashSet.addAll(userList);

        List<User> allUserList = new ArrayList<>(allUsersHashSet);

        for (User objActualUser : allUserList) {
            String recipientEmail = objActualUser.getEmail();

            User objUser = userService.findByEmail(recipientEmail).orElseThrow(() -> new ResourceNotFoundException("noExistDB", "Usuario"));
            String formattedName = formatName(objUser);

            String formattedMessage = "¡Hola " + formattedName + "!\n\n" +
                    "Te informamos que la búsqueda " + objSearchBeforeModification.getName() + " ha sido modificada.\n\n" +
                    "Datos antes de modificación:\n\n" +
                    searchModificationMessage(objSearchBeforeModification, objSearch) +
                    "\nDatos después de modificación:\n\n" +
                    searchModificationMessage(objSearch, objSearchBeforeModification) +
                    "\n¡Gracias y que tengas un buen día!\n\n" +
                    "Soporte ByS";

            customMessageAndSendMail(recipientEmail, "Notificacion de modificación de búsqueda", formattedMessage);
        }
    }

    private String searchModificationMessage(Search objFirstSearch, Search objSecondSearch) {
        String searchBeforeName = objFirstSearch.getName();
        String searchName = objSecondSearch.getName();

        String searchBeforeDateOpening = objFirstSearch.getDateOpening().toString();
        String searchDateOpening = objSecondSearch.getDateOpening().toString();

        String searchBeforeVacancies = Objects.nonNull(objFirstSearch.getVacancies()) ? objFirstSearch.getVacancies().toString() : "-";
        String searchVacancies = Objects.nonNull(objSecondSearch.getVacancies()) ? objSecondSearch.getVacancies().toString() : "-";

        String searchBeforeRemuneration = objFirstSearch.getRemuneration();
        String searchRemuneration = objSecondSearch.getRemuneration();

        String searchBeforeClient = objFirstSearch.getClient().getName();
        String searchClient = objSecondSearch.getClient().getName();

        String searchBeforeProfession = objFirstSearch.getProfession().getName();
        String searchProfession = objSecondSearch.getProfession().getName();

        String searchBeforeSeniority = objFirstSearch.getSeniority().getName();
        String searchSeniority = objSecondSearch.getSeniority().getName();

        String searchBeforeAvailability = objFirstSearch.getAvailability().getName();
        String searchAvailability = objSecondSearch.getAvailability().getName();

        String searchBeforeHiringModality = objFirstSearch.getHiringModality().getName();
        String searchHiringModality = objSecondSearch.getHiringModality().getName();

        String searchBeforeObservations = objFirstSearch.getObservations();
        String searchObservations = objSecondSearch.getObservations();

        String searchBeforeStatus = objFirstSearch.getStatusSearch().getName();
        String searchStatus = objSecondSearch.getStatusSearch().getName();

        String searchBeforeDateEnd = Objects.nonNull(objFirstSearch.getDateEnd()) ?
                objFirstSearch.getDateEnd().toString() : "-";
        String searchDateEnd = Objects.nonNull(objSecondSearch.getDateEnd()) ?
                objSecondSearch.getDateEnd().toString() : "-";

        return showIfNotEqual("Nombre: ", searchBeforeName, searchName) +
                showIfNotEqual("Fecha de apertura: ", searchBeforeDateOpening, searchDateOpening) +
                showIfNotEqual("Fecha de cierre: ", searchBeforeDateEnd, searchDateEnd) +
                showUsers(objFirstSearch.getUserList(), objSecondSearch.getUserList()) +
                showIfNotEqual("Estado: ", searchBeforeStatus, searchStatus) +
                showIfNotEqual("Vacantes: ", searchBeforeVacancies, searchVacancies) +
                showIfNotEqual("Remuneración: ", searchBeforeRemuneration, searchRemuneration) +
                showIfNotEqual("Cliente: ", searchBeforeClient, searchClient) +
                showIfNotEqual("Profesión: ", searchBeforeProfession, searchProfession) +
                showIfNotEqual("Seniority: ", searchBeforeSeniority, searchSeniority) +
                showIfNotEqual("Disponibilidad: ", searchBeforeAvailability, searchAvailability) +
                showSkills(objFirstSearch.getSkillList(), objSecondSearch.getSkillList()) +
                showIfNotEqual("Modalidad de contratación: ", searchBeforeHiringModality, searchHiringModality) +
                showIfNotEqual("Observaciones: ", searchBeforeObservations, searchObservations);
    }

    @Async
    public void customMessageAndSendMail(String recipientEmail, String subject, String formattedMessage) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject(subject);
        message.setText(formattedMessage);
        //javaMailSender.send(message);
    }

    private String showIfNotEqual(String beginMessage, String firstArgument, String secondArgument) {
        if (Objects.nonNull(firstArgument) && Objects.nonNull(secondArgument)) {
            if (!firstArgument.equals(secondArgument)) {
                return beginMessage + firstArgument + "\n";
            }
        }
        return "";
    }

    private String showSkills(List<Skill> firstSkillList, List<Skill> secondSkillList) {
        if (!(firstSkillList.containsAll(secondSkillList) && secondSkillList.containsAll(firstSkillList))) {
            StringBuilder skillListMessage = new StringBuilder().append("Skills: \n");

            for (Skill objSkill : firstSkillList) {
                skillListMessage.append("- ").append(objSkill.getName()).append("\n");
            }
            return skillListMessage.toString();
        }
        return "";
    }

    private String showUsers(List<User> firstUserList, List<User> secondUserList) {
        if (!(firstUserList.containsAll(secondUserList) && secondUserList.containsAll(firstUserList))) {
            StringBuilder userListMessage = new StringBuilder().append("Reclutadores: \n");

            for (User objUser : firstUserList) {
                String formattedName = objUser.getName() + " " + objUser.getLastName();
                userListMessage.append("- ").append(formattedName).append("\n");
            }
            return userListMessage.toString();
        }
        return "";
    }

    private String formatName(User objUser) {
        String name = objUser.getName();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    private String generatePassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        int length = 12;
        StringBuilder objStringBuilder = new StringBuilder(length);
        Random objRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = objRandom.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            objStringBuilder.append(randomChar);
        }

        return objStringBuilder.toString();
    }*/
}
