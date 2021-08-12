package com.thc.adcpressureinapi.controller;


import com.thc.adcpressureinapi.conf.UploadProperties;
import com.thc.adcpressureinapi.response.BaseResult;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;


/**
 *
 */
@Api(tags = "文件上传接口")
@RestController
@RequestMapping("/upload")
@Slf4j
@AllArgsConstructor
public class FileUploadController {


	private UploadProperties uploadProperties;

	@RequestMapping(value = "upload")
	public BaseResult upload(HttpServletRequest request) throws IllegalStateException, IOException {

		HashMap<Object, Object> map = new HashMap<>();
		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
			if (file != null) {
				// 获取文件名
				String originalName = file.getOriginalFilename();

				// 获取文件的后缀名
				String suffixName = originalName.substring(originalName.lastIndexOf("."));

				// 防止文件重名，创建新的文件名
				String name = UUID.randomUUID() + suffixName;

				System.out.println(name);
				// 新建File实例
				File dest = new File(uploadProperties.getFilePath() + name);
				System.out.println(dest);
				// 如果路径不存在，则创建				
		        if(!dest.getParentFile().exists()){
					System.out.println("aaa");
		            dest.getParentFile().mkdirs();//创建父级文件路径
		            dest.createNewFile();//创建文件
		        }

				// 写入文件
				file.transferTo(dest);

				map.put("originalName", originalName);
				map.put("suffix", suffixName);
				map.put("size", file.getSize());
				map.put("name", name);
			}
		}
		return BaseResult.success(map);
	}
}
