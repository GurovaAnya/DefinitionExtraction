package def.domain.gate;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.Objects;

@Component
public class DocumentLoader {

    private String fileBasePath = "";

    public String uploadFile(File file){
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getName()));
        Path path = Paths.get(fileBasePath + fileName);
        try {
            Files.copy(new FileInputStream(file), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/")
                .path(fileName)
                .toUriString();
        return fileDownloadUri;
    }
}
