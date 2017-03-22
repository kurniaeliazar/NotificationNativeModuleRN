package com.rnproject;

import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.uimanager.UIImplementationProvider;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.views.view.ReactViewManager;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by kurniaeliazar on 3/8/17.
 */
public class TestSampleTest extends ReactIntegrationTestCase {


    private interface TestJSLocaleModule extends JavaScriptModule {
        void toUpper(String string);
        void toLower(String string);
    }

    StringRecordingModule mStringRecordingModule;

    private CatalystInstance mInstance;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        List<ViewManager> viewManagers = Arrays.<ViewManager>asList(
                new ReactViewManager());
        final UIManagerModule mUIManager = new UIManagerModule(
                getContext(),
                viewManagers,
                new UIImplementationProvider(),false);
        UiThreadUtil.runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {
                        mUIManager.onHostResume();
                    }
                });
        waitForIdleSync();

        mStringRecordingModule = new StringRecordingModule();
        mInstance = ReactTestHelper.catalystInstanceBuilder(this)
                .addNativeModule(mStringRecordingModule)
                .addNativeModule(mUIManager)
                .addNativeModule(new FakeWebSocketModule())
                .addJSModule(TestJSLocaleModule.class)
                .build();
    }

    public void testToUpper() {
        TestJSLocaleModule testModule = mInstance.getJSModule(TestJSLocaleModule.class);
        waitForBridgeAndUIIdle();

        testModule.toUpper("test");
        testModule.toUpper("W niżach mógł zjeść truflę koń bądź psy");
        testModule.toUpper("Шеф взъярён тчк щипцы с эхом гудбай Жюль");
        testModule.toUpper("Γαζίες καὶ μυρτιὲς δὲν θὰ βρῶ πιὰ στὸ χρυσαφὶ ξέφωτο");
        testModule.toUpper("chinese: 幓 厏吪吙 鈊釿閍 碞碠粻 曮禷");
        waitForBridgeAndUIIdle();

        String[] answers = mStringRecordingModule.getCalls().toArray(new String[0]);
        assertEquals("TEST", answers[0]);
        assertEquals("W NIŻACH MÓGŁ ZJEŚĆ TRUFLĘ KOŃ BĄDŹ PSY", answers[1]);
        assertEquals("ШЕФ ВЗЪЯРЁН ТЧК ЩИПЦЫ С ЭХОМ ГУДБАЙ ЖЮЛЬ", answers[2]);
        assertEquals("ΓΑΖΊΕΣ ΚΑῚ ΜΥΡΤΙῈΣ ΔῈΝ ΘᾺ ΒΡΩ͂ ΠΙᾺ ΣΤῸ ΧΡΥΣΑΦῚ ΞΈΦΩΤΟ", answers[3]);
        assertEquals("CHINESE: 幓 厏吪吙 鈊釿閍 碞碠粻 曮禷", answers[4]);
    }

    public void testToLower() {
        TestJSLocaleModule testModule = mInstance.getJSModule(TestJSLocaleModule.class);

        testModule.toLower("TEST");
        testModule.toLower("W NIŻACH MÓGŁ ZJEŚĆ TRUFLĘ KOŃ BĄDŹ psy");
        testModule.toLower("ШЕФ ВЗЪЯРЁН ТЧК ЩИПЦЫ С ЭХОМ ГУДБАЙ ЖЮЛЬ");
        testModule.toLower("ΓΑΖΊΕΣ ΚΑῚ ΜΥΡΤΙῈΣ ΔῈΝ ΘᾺ ΒΡΩ͂ ΠΙᾺ ΣΤῸ ΧΡΥΣΑΦῚ ΞΈΦΩΤΟ");
        testModule.toLower("CHINESE: 幓 厏吪吙 鈊釿閍 碞碠粻 曮禷");
        waitForBridgeAndUIIdle();

        String[] answers = mStringRecordingModule.getCalls().toArray(new String[0]);
        assertEquals("test", answers[0]);
        assertEquals("w niżach mógł zjeść truflę koń bądź psy", answers[1]);
        assertEquals("шеф взъярён тчк щипцы с эхом гудбай жюль", answers[2]);
        assertEquals("γαζίες καὶ μυρτιὲς δὲν θὰ βρῶ πιὰ στὸ χρυσαφὶ ξέφωτο", answers[3]);
        assertEquals("chinese: 幓 厏吪吙 鈊釿閍 碞碠粻 曮禷", answers[4]);
    }


}