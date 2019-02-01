package pl.edu.wat.wcy.pz.project.mailsender.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class EmailTemplates {

    private ResourceLoader resourceLoader;

    private String verificationTemplateBody;
    private String verificationTemplateSubject;

    private String resetPasswordTemplateBody;
    private String resetPasswordTemplateSubject;

    public EmailTemplates(ResourceLoader resourceLoader) throws IOException {

        this.resourceLoader = resourceLoader;

        Resource resource = resourceLoader.getResource("classpath:VerificationMailTemplateBody.txt");
        InputStream inputStream = resource.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        StringBuilder out = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            out.append(line);
        }
        this.verificationTemplateBody = out.toString();

        resource = resourceLoader.getResource("classpath:VerificationMailTemplateSubject.txt");
        inputStream = resource.getInputStream();
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        out = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            out.append(line);
        }
        this.verificationTemplateSubject = out.toString();

        resource = resourceLoader.getResource("classpath:ResetPasswordMailTemplateBody.txt");
        inputStream = resource.getInputStream();
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        out = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            out.append(line);
        }
        this.resetPasswordTemplateBody = out.toString();

        resource = resourceLoader.getResource("classpath:ResetPasswordMailTemplateSubject.txt");
        inputStream = resource.getInputStream();
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        out = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            out.append(line);
        }
        this.resetPasswordTemplateSubject = out.toString();

        System.out.println(verificationTemplateBody);
        System.out.println(verificationTemplateSubject);
        System.out.println(resetPasswordTemplateBody);
        System.out.println(resetPasswordTemplateSubject);
    }

    public String getVerificationEmailBody(String name, String link) {
        return verificationTemplateBody.replace("{name}", name).replace("{link}", link);
    }

    public String getVerificationTemplateSubject() {
        return verificationTemplateSubject;
    }

    public String getResetPasswordTemplateBody(String name, String password) {
        return resetPasswordTemplateBody.replace("{name}", name).replace("{password}", password);
    }

    public String getResetPasswordTemplateSubject() {
        return resetPasswordTemplateSubject;
    }
}
