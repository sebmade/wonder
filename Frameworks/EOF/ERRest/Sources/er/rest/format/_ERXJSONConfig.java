package er.rest.format;

import java.util.Date;
import java.util.Set;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.processors.JsonValueProcessorMatcher;

import com.webobjects.foundation.NSTimestamp;

import er.rest.ERXRestContext;
import er.rest.ERXRestUtils;

public class _ERXJSONConfig {
	public static final class ERXRestValueProcessorMatcher extends JsonValueProcessorMatcher {
		@Override
		public Object getMatch(@SuppressWarnings("rawtypes") Class target, @SuppressWarnings("rawtypes") Set set) {
			if (target != null && set != null && set.contains(target)) {
				return target;
			}
			else {
				return null;
			}
		}
	}

	public static class NSTimestampProcessor implements JsonValueProcessor {
		private ERXRestContext _context;
		
		public NSTimestampProcessor(ERXRestContext context) {
			_context = context;
		}
		
		public Object processArrayValue(Object obj, JsonConfig jsonconfig) {
			return ERXRestUtils.coerceValueToString(obj, _context);
		}

		public Object processObjectValue(String s, Object obj, JsonConfig jsonconfig) {
			return ERXRestUtils.coerceValueToString(obj, _context);
		}
	}

	public static JsonConfig createDefaultConfig(ERXRestContext context) {
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(NSTimestamp.class, new NSTimestampProcessor(context));
		config.registerJsonValueProcessor(Date.class, new NSTimestampProcessor(context));
		config.setJsonValueProcessorMatcher(new ERXRestValueProcessorMatcher());
		return config;
	}
	
}
