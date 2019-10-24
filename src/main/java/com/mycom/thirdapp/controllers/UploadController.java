package com.mycom.thirdapp.controllers;


import com.mycom.thirdapp.db.models.ReportModel;
import com.mycom.thirdapp.services.ReportParser;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Controller
public class UploadController {

    @Autowired
    ReportParser reportParser;

    @GetMapping("/report/upload")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file1") MultipartFile errorFile,
                                   @RequestParam("file2") MultipartFile exceptionFile,
                                   RedirectAttributes redirectAttributes) throws IOException {
        if (errorFile.isEmpty() || exceptionFile.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        List<ReportModel> result = reportParser.processFiles(convert(errorFile), convert(exceptionFile));
        saveFile(result);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded '" + errorFile.getOriginalFilename() + "'");

        return "redirect:/uploadStatus";
    }

    private void saveFile(List<ReportModel> result) {
        File file = new File("newfile.csv");
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile,
                    CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.NO_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            // adding header to csv
            String[] header = { "Date", "Service", "Errors", "Exceptions" };
            writer.writeNext(header);

            // add data to csv
            for ( ReportModel row:result ) {
                writer.writeNext(new String[]{row.date,row.service,row.errorCount,row.exceptionCount});
            }

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    public static File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}
