package com.example.pagefactory;

import com.example.view.BuyAdapter;
import com.example.view.ReadAdapter;
import com.example.view.ReadTextViewHelper;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private ViewPager readVp;
	
	private ReadAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		
		setContentView(R.layout.activity_main);
		
		String content = "我叫文西，是一个狗愤没有狗怎么做狗愤？\n我有一只狗，我特别爱它，所以我也给他取名为文西不为别的，狗有狗样，我只希望文西像我一样优秀我每天最爱做的就是抱着文西坐在电脑前，它一口我一口地吃着泡面，一边刷着网页我特别不明白，为什么有人吃狗肉？\n贴吧里时常有些杂碎，吃了狗还不满足，非要发帖以示荣耀。这简直挑战了我的底线，我要代表所有的狗，发起圣战！因为文西从来就不是一个人。我会在每一个关于食狗肉的帖子下面，默默留下吃狗者死全家！的字眼，深藏功与名。有些时候，听到某某城市狗咬人这样的新闻我都觉得\n心痛。狗听话，懂事，是人类的好伙伴，要不是这些天杀的欺负狗，狗怎么会咬人？这些傻逼们一天不注意自己的言行举止，狗狗们就难以和人类共享同一片蓝天。捍卫权利，圣战不息！我握着文西的爪子一同宣誓。我和文西都爱吃肉，无论鸡鸭鱼、猪牛羊。他妈的，还是狗最高贵！文西是一只特别的狗，它比一般的狗要高大，身上散发着迷人的气息。我觉得它一定是一个有出息的狗。我执着于教它讲人话\n我叫文西，是一个狗愤没有狗怎么做狗愤？\n我有一只狗，我特别爱它，所以我也给他取名为文西不为别的，狗有狗样，我只希望文西像我一样优秀我每天最爱做的就是抱着文西坐在电脑前，它一口我一口地吃着泡面，一边刷着网页我特别不明白，为什么有人吃狗肉？\n贴吧里时常有些杂碎，吃了狗还不满足，非要发帖以示荣耀。这简直挑战了我的底线，我要代表所有的狗，发起圣战！因为文西从来就不是一个人。我会在每一个关于食狗肉的帖子下面，默默留下吃狗者死全家！的字眼，深藏功与名。有些时候，听到某某城市狗咬人这样的新闻我都觉得\n心痛。狗听话，懂事，是人类的好伙伴，要不是这些天杀的欺负狗，狗怎么会咬人？这些傻逼们一天不注意自己的言行举止，狗狗们就难以和人类共享同一片蓝天。捍卫权利，圣战不息！我握着文西的爪子一同宣誓。我和文西都爱吃肉，无论鸡鸭鱼、猪牛羊。他妈的，还是狗最高贵！文西是一只特别的狗，它比一般的狗要高大，身上散发着迷人的气息。我觉得它一定是一个有出息的狗。我执着于教它讲人话\n作者说：这是一篇测试文章，好像有bug啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊";
		ReadTextViewHelper t = new ReadTextViewHelper(this,50, content);

		readVp = (ViewPager) findViewById(R.id.vp_reader);
		
		adapter = new ReadAdapter(t.getAllViews());
		
		readVp.setAdapter(adapter);
	}
}
