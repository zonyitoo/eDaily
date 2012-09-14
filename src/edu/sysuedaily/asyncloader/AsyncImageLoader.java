package edu.sysuedaily.asyncloader;

import java.lang.ref.SoftReference;
import java.util.HashMap;

import edu.sysuedaily.cache.ImageCache;
import edu.sysuedaily.request.RequestUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;


public class AsyncImageLoader {
	private Object lock = new Object();
	private boolean allowedLoad = true;
	private boolean firstLoad = true;
	private int startLoadRow = 0;
	private int endLoadRow = 0;
	
	private boolean netStatus = false;
	private Context context = null;
	
	private final Handler handler = new Handler();
	private static HashMap<String, SoftReference<Bitmap>> ramImageCache = new HashMap<String, SoftReference<Bitmap>>();
	
	
	public interface ImageLoadListener {
		public void onImageLoad(Integer rowNum, Bitmap bitmap);
		public void onError(Integer rowNum);
	}
	
	public AsyncImageLoader(boolean netStatus, Context context) {
		this.netStatus = netStatus;
		this.context = context;
		
	}
	
	public void setLoadRowLimit(int start, int end) {
		if (start > end) 
			return;
		this.startLoadRow = start;
		this.endLoadRow = end;
	}
	
	public void reset() {
		this.allowedLoad = true;
		this.firstLoad = true;
	}
	
	public void lock() {
		this.allowedLoad = false;
		this.firstLoad = false;
	}
	
	public void unlock() {
		this.allowedLoad = true;
		synchronized (lock) {
			lock.notifyAll();
		}
	}
	
	public void loadImage(Integer rowNum, String imgeaUrl, ImageLoadListener listener, int requireSize) {
		final ImageLoadListener loadListener = listener;
		final Integer row = rowNum;
		final String imageURL = imgeaUrl;
		final int requireSIZE = requireSize;
		//Log.d(Constant.LOG_TAG, "call loadImage");
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (AsyncImageLoader.this.allowedLoad == false) {
					synchronized (lock) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else {
					if (allowedLoad == true && firstLoad == true) {
						loadImagePri(row, imageURL, loadListener, requireSIZE);
					}
					else if (allowedLoad == true && row >= startLoadRow && row <= endLoadRow) {
						loadImagePri(row, imageURL, loadListener, requireSIZE);
					}
				}
			}
		}).start();
	}
	
	private void loadImagePri(final Integer rowNum, String imageUrl, final ImageLoadListener listener,
			int requireSize) {
		if (ramImageCache.containsKey(imageUrl)) {
			SoftReference<Bitmap> sfBitmap = ramImageCache.get(imageUrl);
			final Bitmap bitmap = sfBitmap.get();
			//Log.d(Constant.LOG_TAG, "load from ramImageCache");
			if (bitmap != null) {
				this.handler.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						listener.onImageLoad(rowNum, bitmap);
					}
				});
				return;
			}
		}
		else {
			if (this.netStatus == true) {
				this.loadImageFromNet(rowNum, imageUrl, listener, requireSize);
				//Log.d(Constant.LOG_TAG, "load from net"); // called 8 times....
			}
			else {
				this.loadImageFromCache(rowNum, imageUrl, listener, requireSize);
				//Log.d(Constant.LOG_TAG, "load from cache");
			}
		}
	}
	
	private void loadImageFromNet(final Integer rowNum, String imageUrl, final ImageLoadListener listener,
			int requireSize) {
		try {
			//Log.d(Constant.LOG_TAG, "in loadImageFromNet, the imageUrl is " + imageUrl);
			if (imageUrl != null) {
				final Bitmap bitmap = RequestUtil.urlToBitmap(imageUrl, requireSize);
				ImageCache.getImageCache(context).cacheImage(bitmap, imageUrl);
				if (bitmap != null) {
					ramImageCache.put(imageUrl, new SoftReference<Bitmap>(bitmap));
					//ImageCache.getImageCache().cacheImage(bitmap, imageUrl);
					handler.post(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							listener.onImageLoad(rowNum, bitmap);
						}
					
					});
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// url is error
			handler.post(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					listener.onError(rowNum);
				}
				
			});
		}
	}
	
	private void loadImageFromCache(final Integer rowNum, String imageUrl, final ImageLoadListener listener, 
			int requireSize) {
		try {
			//Log.d(Constant.LOG_TAG, "in loadImageFromNet, the imageUrl is " + imageUrl);
			if (imageUrl != null) {
				final Bitmap bitmap = ImageCache.getImageCache(this.context).getCacheImage(imageUrl);
				if (bitmap != null) {
					ramImageCache.put(imageUrl, new SoftReference<Bitmap>(bitmap));
					//ImageCache.getImageCache().cacheImage(bitmap, imageUrl);
					handler.post(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							listener.onImageLoad(rowNum, bitmap);
						}
					
					});
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// url is error
			handler.post(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					listener.onError(rowNum);
				}
				
			});
		}
	}
	
	public static void cleanRamCache() {
		ramImageCache.clear();
	}
}
