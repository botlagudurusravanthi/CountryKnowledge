package com.info.countryknowledge.adapter;

import com.info.countryknowledge.model.RowData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Sravanthi_B01 on 6/4/2018.
 */
@RunWith(PowerMockRunner.class)
public class CountryInfoListAdapterTest {
    @Mock
    List<RowData> rowDataList;
    @Mock
    CountryInfoListAdapter adapter;

    @Before
    public void setUp() throws Exception {

    }
    @Test
    public void testItemCount() throws Exception {
        adapter.setRowData(rowDataList);
        assertEquals(adapter.getItemCount(),rowDataList.size());
    }


}