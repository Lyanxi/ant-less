package convert;

import org.apache.tools.ant.Task;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class LessToCss extends Task {
    private String directory;

    public static void main(String[] args) {
        new LessToCss().cssConvert("/Users/iohanax/Documents/Github/ant-less/src/less/");
    }

    public void setDirectory(String directory) {
        this.directory = directory + "/";
    }

    public void execute() {
        if (directory != null) {
            cssConvert(directory);
        }
    }
    //convert the all less files under the path to css files
    private void cssConvert(String path) {
        File lessFiles = new File(path);
        //filter the less files
        File[] files = lessFiles.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {
                File filterFile = new File(file + "/" + s);
                return filterFile.isFile() ? s.endsWith(".less") : true;
            }
        });
        for (File file : files) {
            //recurs the less directory except the css directory
            if (file.isDirectory()) {
                if (file.getName().equals("css")) {
                    continue;
                }
                cssConvert(file.getAbsolutePath());
            } else {
                try {
                    String lessName = file.getName();
                    String prefixName = lessName.substring(0, lessName.indexOf("."));
                    //the path to put the all css files
                    String targetPath = path + "css/" + prefixName + ".css";
                    //execute the shellscript less.sh
                    String command = "sh " + directory + "less.sh " + file.getAbsolutePath() + " " + targetPath;
                    Runtime.getRuntime().exec(command);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
