package com.labs.tools.cases;

import android.test.AndroidTestCase;

import com.labs.tools.api.ContributeApi;

/**
 * Created by Vikraa on 1/13/2016.
 */
public class ContributeApiTest extends AndroidTestCase {
    private ContributeApi mContributeApi;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mContributeApi = new ContributeApi(getContext());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testIsNumberContributed() {
        boolean actual = mContributeApi.isNumberContributed("12345678");
        assertEquals(true, actual);
    }
}
