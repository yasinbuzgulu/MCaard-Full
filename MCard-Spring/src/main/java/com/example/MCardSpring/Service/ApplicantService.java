package com.example.MCardSpring.Service;

import com.example.MCardSpring.Exception.ApplicantBadRequestException;
import com.example.MCardSpring.Exception.ApplicantNotFoundException;
import com.example.MCardSpring.MainModel.Applicant;
import com.example.MCardSpring.Repository.ApplicantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CRUD işlemlerini yapan servis sınıfım
 */
@Service
@Transactional
public class ApplicantService {
    /**
     * Service içinde kullanılacak applicant repository için instance oluşturmadan constructor ile çağrılır
     */
    ApplicantRepository applicantRepository;


    public ApplicantService(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    /**
     * Yeni başvuran oluşturma metodu
     *
     * @param applicant: yeni başvuran nesnesi
     * @return: repository ye kayıt döner
     */
    public Applicant createApplicant(Applicant applicant) throws ParseException {
        validateApplicant(applicant);
        return applicantRepository.save(applicant);

    }

    /**
     * id ye sahip başvuranı silen metot
     *
     * @param id: siinecek başvuran id si
     */
    public void deleteApplicant(Long id) {

        try {
            applicantRepository.deleteById(id);
        } catch (Exception e) {
            throw new ApplicantNotFoundException(id);
        }
    }

    /**
     * Tüm başvuranları listeleyen metot
     *
     * @return: tüm başvuranları repositoryden bulup döner
     */
    public List<Applicant> listApplicants() {
        return new ArrayList<>(applicantRepository.findAll());
    }

    /**
     * id ye sahip başvuranı bulur
     *
     * @param id: çağırılan başvuranın id si
     * @return: girilen id li başvuranı döner
     */
    public Applicant getApplicantById(Long id) {
        return applicantRepository.findById(id)
                .orElseThrow(() -> new ApplicantNotFoundException(id));
    }

    /**
     * Başvuranın bilgi güncellemesini yapan sınıf
     *
     * @param newApplicant: güncellenmiş yeni başvuran nesnesi
     * @param id:           düzenlemenin yapıldığı başvuranın id si
     */
    public void updateApplicant(Applicant newApplicant, Long id) throws ParseException {
        Applicant applicant = applicantRepository.findById(id).get();
        validateApplicant(newApplicant);

        applicant.setName(newApplicant.getName());
        applicant.setSurname(newApplicant.getSurname());
        applicant.setBirthDate(newApplicant.getBirthDate());
        applicant.setCitizenNumber(newApplicant.getCitizenNumber());
        applicant.setTypeBasedOnAge(newApplicant.getTypeBasedOnAge());
        applicant.setTypeBasedOnEducation(newApplicant.getTypeBasedOnEducation());
        applicantRepository.save(applicant);
    }

    void validateApplicant(Applicant applicant) throws ParseException {
        if (applicant.getName() == null || applicant.getSurname() == null || applicant.getBirthDate() == null ||
                applicant.getCitizenNumber() <= 0 || applicant.getTypeBasedOnAge() == null || applicant.getTypeBasedOnEducation() == null) {
            throw new ApplicantBadRequestException(applicant.getId());
        }
        String strNumber = String.valueOf(applicant.getCitizenNumber());
        if (strNumber.length() != 11 || strNumber.charAt(0) == '0') {
            throw new ApplicantBadRequestException(applicant.getId());
        }
        int oddSum = 0, evenSum = 0, controlDigit = 0;
        for (int i = 0; i <= 8; i++) {
            if (i % 2 == 0) {
                oddSum += Character.getNumericValue(strNumber.charAt(i));

            } else {
                evenSum += Character.getNumericValue(strNumber.charAt(i));
            }
        }
        controlDigit = (oddSum * 7 - evenSum) % 10;
        if (Character.getNumericValue(strNumber.charAt(9)) != controlDigit) {
            throw new ApplicantBadRequestException(applicant.getId());
        }
        if (Character.getNumericValue(strNumber.charAt(10)) != (controlDigit + evenSum + oddSum) % 10) {
            throw new ApplicantBadRequestException(applicant.getId());
        }

        String regexDate = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|(?:(?:1[6-9]|[2-9]\\d)?\\d{2})(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\5(?:0?[1-9]|1\\d|2[0-8])$|^(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00)))(\\/|-|\\.)0?2\\6(29)$|^(?:(?:1[6-9]|[2-9]\\d)?\\d{2})(?:(?:(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\8(?:29|30))|(?:(\\/|-|\\.)(?:0?[13578]|1[02])\\9(?:31)))$\n";
        String regexLetter = "^[a-zA-Z]*$";
        String regexNumber = "^[0-9]*$";
        Pattern patternDate = Pattern.compile(regexDate);
        Pattern patternLetter = Pattern.compile(regexLetter);
        Pattern patternNumber = Pattern.compile(regexNumber);

        Matcher matcherDate = patternDate.matcher(applicant.getBirthDate());
        Matcher matcherName = patternLetter.matcher(applicant.getName());
        Matcher matcherSurname = patternLetter.matcher(applicant.getSurname());
        Matcher matcherNumber = patternNumber.matcher(String.valueOf(applicant.getCitizenNumber()));

        if (!matcherDate.matches() || !matcherName.matches() || !matcherSurname.matches() || !matcherNumber.matches()) {
            throw new ApplicantBadRequestException(applicant.getId());
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDate = sdf.parse(applicant.getBirthDate());
        Date now = new Date(System.currentTimeMillis());

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(now.getTime() - birthDate.getTime());
        int yearDifference = c.get(Calendar.YEAR) - 1970;
        int monthDifference = c.get(Calendar.MONTH);
        int dayDifference = c.get(Calendar.DAY_OF_MONTH) - 1;
        if (yearDifference < 0 || yearDifference > 150) {
            throw new ApplicantBadRequestException(applicant.getId());
        }
    }
}
