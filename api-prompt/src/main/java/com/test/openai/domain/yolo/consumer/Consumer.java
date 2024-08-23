package com.test.openai.domain.yolo.consumer;

import com.test.openai.domain.yolo.consumer.service.YoloService;
import com.test.openai.image.ImageUploader;
import com.test.openai.image.S3ImageUploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@Component
public class Consumer {

    private final S3ImageUploader imageUploader;
    private final YoloService yoloService;

    @RabbitListener(queues = "image.queue")
    public void imageCaptureConsumer(byte[] image) {
        log.info("image capture consumer called");
        // yoloService.detect(image);
        /*
        redis 저장

        imageUploader.upload();
         */
    }
}
