package com.lshzzz.mato.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Slf4j  // Lombok 기반 로거 사용
@RestController
@RequestMapping("/upload")
public class UploadController {

	private static final String AUDIO_SAVE_PATH = "C:/audio/";

	@PostMapping("/youtube")
	public String downloadYoutubeAudio(@RequestParam String url) {
		try {
			ProcessBuilder builder = new ProcessBuilder(
				"yt-dlp", "-x", "--audio-format", "mp3", "-o", AUDIO_SAVE_PATH + "%(title)s.%(ext)s", url
			);
			builder.redirectErrorStream(true);
			Process process = builder.start();

			try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
				String line;
				while ((line = reader.readLine()) != null) {
					log.info(line);  // 표준 출력 → 로그로 변경
				}
			}

			process.waitFor();
			return "Download complete!";
		} catch (Exception e) {
			log.error("유튜브 오디오 다운로드 실패: {}", e.getMessage(), e);
			return "Error downloading audio.";
		}
	}

	@PostMapping("/file")
	public String uploadFile(@RequestParam MultipartFile file) {
		try {
			File targetFile = new File(AUDIO_SAVE_PATH + file.getOriginalFilename());
			file.transferTo(targetFile);
			log.info("파일 업로드 성공: {}", targetFile.getAbsolutePath());
			return "File uploaded successfully!";
		} catch (Exception e) {
			log.error("파일 업로드 실패: {}", e.getMessage(), e);
			return "Error uploading file.";
		}
	}
}
