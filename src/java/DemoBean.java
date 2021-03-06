
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

@ManagedBean
@SessionScoped
@MultipartConfig()
public class DemoBean {

    private Part file;
    private String destination;

    public void setDestination(String Destination) {
        this.destination = Destination;
    }

    public String getDestination() {
        return destination;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public Part getFile() {
        return file;
    }

    public String upload() throws IOException {
//        UnZip zipFile = new UnZip();
//        zipFile.unZipIt("E:\\MyFilee.zip", "E:\\outputzip2");

        if (new File(destination).exists()) {
            file.write(destination + getFileName(file));
            return "success";
        }
        return "fail";

    }

    public String getFileName(Part part) {
        for (String cd : part.getHeader("Content-Disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf("=") + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
            }
        }
        return null;

    }

}
