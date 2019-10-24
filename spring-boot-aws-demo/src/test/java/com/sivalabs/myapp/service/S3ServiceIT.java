package com.sivalabs.myapp.service;

import com.amazonaws.services.s3.model.S3Object;
import com.sivalabs.myapp.AbstractIntegrationTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class S3ServiceIT extends AbstractIntegrationTest {

    @Autowired
    S3Service s3Service;

    String bucketName = "test-bucket-1";

    @Before
    public void setUp() {
        s3Service.createBucket(bucketName);
    }

    @After
    public void tearDown() {
        s3Service.deleteBucketForce(bucketName);
    }

    @Test
    public void should_store_data() {
        S3Object s3Object = s3Service.store(bucketName, "my-key-1", "my-value-1");
        assertThat(s3Object.getObjectContent()).hasSameContentAs(new ByteArrayInputStream("my-value-1".getBytes()));
    }
}
