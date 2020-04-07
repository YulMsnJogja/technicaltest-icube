package com.icube.branch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.icube.branch.model.Branch;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

@SpringBootTest
class BranchControllerTest extends AbstractTest{
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getBranchList() throws Exception {
        String uri = "/list";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Branch[] productlist = super.mapFromJson(content, Branch[].class);
        assertTrue(productlist.length > 0);
    }

    @Test
    public void createBranch() throws Exception {
        String uri = "/";
        Branch branch = new Branch();
        branch.setName("Dibli.Inc");
        branch.setAddress("Chinatown");
        branch.setOperatingHours("08.00-16.30");
        branch.setCreateOn(new Date(02-04-2020));
        branch.setDeleted(true);

        String inputJson = super.mapToJson(branch);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Branch is created successfully");
    }
    @Test
    public void updateBranch() throws Exception {
        String uri = "/update/1";
        Branch branch = new Branch();
        branch.setName("Dibli Branch");
        branch.setAddress("Chinatown");
        branch.setOperatingHours("08.00-16.30");
        branch.setCreateOn(new Date(01-04-2020));
        branch.setDeleted(true);

        String inputJson = super.mapToJson(branch);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Branch is updated successsfully");
    }
    @Test
    public void deleteBranch() throws Exception {
        String uri = "/delete/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Branch is deleted successsfully");
    }
}
