var PORT = 80;       //端口
var DIR = 'view';     //用于存放html的目录

var http = require('http');
var url=require('url');
var fs=require('fs');
var mine=require('./mine').types;
var path=require('path');
var logger = require("./log").logger("server");

var server = http.createServer(function (request, response) {
    var pathname = url.parse(request.url).pathname;
    if (pathname == "/"){
    	pathname = "/jobvisdom.html";
    }
    var realPath = path.join(DIR, pathname);
    
    var ext = path.extname(realPath);
    ext = ext ? ext.slice(1) : 'unknown';
    //if(ext == "html"){logger.info(request.headers);}
    fs.exists(realPath, function (exists) {
        if (!exists) {
            response.writeHead(404, {
                'Content-Type': 'text/plain'
            });

            response.write("This request URL " + pathname + " was not found on this server.");
            response.end();
        } else {
            fs.readFile(realPath, "binary", function (err, file) {
                if (err) {
                    response.writeHead(500, {
                        'Content-Type': 'text/plain'
                    });
                    response.end(err);
                } else {
                    var contentType = mine[ext] || "text/plain";
                    response.writeHead(200, {
                        'Content-Type': contentType
                    });
                    response.write(file, "binary");
                    response.end();
                }
            });
        }
    });
});
server.listen(PORT);

logger.info("Server runing at port: " + PORT + ".");