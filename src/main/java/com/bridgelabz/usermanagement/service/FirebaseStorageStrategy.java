package com.bridgelabz.usermanagement.service;

import com.bridgelabz.usermanagement.helper.FirebaseCredential;
import com.bridgelabz.usermanagement.response.Responce;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.channels.Channels;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.Objects;

@Service
public class FirebaseStorageStrategy {

    public String projectId;
    @Autowired
    Environment environment;
    private StorageOptions storageOptions;
    private String bucketName;

    @PostConstruct
    private void initializeFirebase() throws Exception {

        bucketName = environment.getRequiredProperty("firebase_bucket_name");
        projectId = environment.getRequiredProperty("project_id");

        InputStream firebaseCredential = createFirebaseCredential();
        this.storageOptions = StorageOptions.newBuilder()
                .setProjectId(projectId)
                .setCredentials(GoogleCredentials.fromStream(firebaseCredential)).build();
    }

    public String uploadFile(MultipartFile multipartFile) throws IOException {
        File file = convertMultiPartToFile(multipartFile);
        Path filePath = file.toPath();
        String objectName = generateFileName(multipartFile);

        Storage storage = storageOptions.getService();

        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        Blob blob = storage.create(blobInfo, Files.readAllBytes(filePath));

        return objectName;
    }


    public ResponseEntity<Object> downloadFile(String fileName) throws Exception {

        if (fileName.equals("undefined")) {
            return new ResponseEntity(new Responce(HttpStatus.NOT_FOUND.value()
                    , "no image found"), HttpStatus.NOT_FOUND);
        }
        Storage storage = storageOptions.getService();

        Blob blob = storage.get(BlobId.of(bucketName, fileName));

        ReadChannel reader = blob.reader();

        InputStream inputStream = Channels.newInputStream(reader);

        byte[] content;

        content = IOUtils.toByteArray(inputStream);

        final ByteArrayResource byteArrayResource = new ByteArrayResource(content);

        return ResponseEntity
                .ok()
                .contentLength(content.length)
                .header("Content-type", "application/octet-stream")
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(byteArrayResource);

    }


    private File convertMultiPartToFile(MultipartFile file) throws IOException {

        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();

        return convertedFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + Objects.requireNonNull(multiPart.getOriginalFilename()).replace(" ", "_");
    }

    private InputStream createFirebaseCredential() throws Exception {

        FirebaseCredential firebaseCredential = new FirebaseCredential();
        String privateKey = environment.getRequiredProperty("private_key").replace("\\n", "\n");
        firebaseCredential.setType(environment.getRequiredProperty("type"));
        firebaseCredential.setProject_id(projectId);
        firebaseCredential.setPrivate_key_id("private_key_id");
        firebaseCredential.setPrivate_key(privateKey);
        firebaseCredential.setClient_email(environment.getRequiredProperty("client_email"));
        firebaseCredential.setClient_id(environment.getRequiredProperty("client_id"));
        firebaseCredential.setAuth_uri(environment.getRequiredProperty("auth_uri"));
        firebaseCredential.setToken_uri(environment.getRequiredProperty("token_uri"));
        firebaseCredential.setAuth_provider_x509_cert_url(environment.getRequiredProperty("auth_provider_x509_cert_url"));
        firebaseCredential.setClient_x509_cert_url(environment.getRequiredProperty("client_x509_cert_url"));

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(firebaseCredential);

        return new ByteArrayInputStream(jsonString.getBytes());
    }
}