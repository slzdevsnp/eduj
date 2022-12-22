package com.tutorialspoint.io

class FileOps {
    void readFile(def filename){
        new File(filename).eachLine {
            line -> println "line: $line"
        }
    }
    String file2Str(String filename){
        return new File(filename).text
    }

    void writeFile(String filename, def contents){
        new File(filename).withWriter('utf-8') { writer ->
            writer.write(contents)
        }
    }
    long getFileSize(String filename){
        return new File(filename).length()
    }
    boolean isFileADirectory(String filename){
        return new File(filename).isDirectory()
    }

}

FileOps fr = new FileOps()
String fn='/home/zimine/repos/clearn/java8/groovy/groovy_lang/src/com/tutorialspoint/io/ReadFile.groovy'
fr.readFile(fn)

def contents = fr.file2Str(fn)
//String contents = fr.file2Str(fn) // the same
println("##############################")
println "file contents: ${contents}"

String fout = '/tmp/output.txt'
fr.writeFile(fout, contents)
println "Written output file has length   ${fr.getFileSize(fout)}  in bytes.."

println "Check if output file is a directory: ${fr.isFileADirectory(fout)}"