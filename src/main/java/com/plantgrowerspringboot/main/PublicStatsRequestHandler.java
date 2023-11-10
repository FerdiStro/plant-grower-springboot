package com.plantgrowerspringboot.main;

import com.plantgrowerspringboot.main.repository.Repository;
import com.plantgrowerspringboot.main.stats.CreateStats;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.Base64;



@Component
@Slf4j
public class PublicStatsRequestHandler  {
    private Repository repository;

    public PublicStatsRequestHandler(Repository repository) {
        this.repository = repository;
    }


    public ResponseEntity<Resource> getStatsImageByName(String name, String contentType) {
        CreateStats stats  =  new CreateStats(repository);
        log.info("Stats for: " +  name + "  |  contentType: " + contentType);
        MediaType type =  getMediaType(contentType);


        String base64Image = stats.getStatsAsBase64String(name);
        try {
            if (base64Image != null && !base64Image.isEmpty()) {
                byte[] imageBytes = Base64.getDecoder().decode(base64Image);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(type);
                headers.setContentLength(imageBytes.length);
                headers.setContentDispositionFormData("attachment", name + "Stats."+contentType);
                ByteArrayResource resource = new ByteArrayResource(imageBytes);
                return new ResponseEntity<>(resource, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private MediaType getMediaType(String contentType) {
        return switch (contentType) {
            case "jpg" -> MediaType.IMAGE_JPEG;
            case "png" -> MediaType.IMAGE_PNG;
            case "gif" -> MediaType.IMAGE_GIF;
            default -> null;
        };
    }


}
