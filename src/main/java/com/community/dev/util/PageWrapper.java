package com.community.dev.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageWrapper<T> {

	public static final int MAX_PAGE_ITEM_DISPLAY = 10;

	private Page<T> page;
	private List<PageItem> pageItems;
	private int currentNumber;
	private String url;

	public PageWrapper(Page<T> page, String url) {
		this.page = page;
		this.url = url;
		pageItems = new ArrayList<PageItem>();

		currentNumber = page.getNumber() + 1; // start from 1 to match page.page

		int start, size;
		if (page.getTotalPages() <= MAX_PAGE_ITEM_DISPLAY) {
			start = 1;
			size = page.getTotalPages();
		} else {
			if (currentNumber <= MAX_PAGE_ITEM_DISPLAY - MAX_PAGE_ITEM_DISPLAY / 2) {
				start = 1;
				size = MAX_PAGE_ITEM_DISPLAY;
			} else if (currentNumber >= page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY / 2) {
				start = page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY + 1;
				size = MAX_PAGE_ITEM_DISPLAY;
			} else {
				start = currentNumber - MAX_PAGE_ITEM_DISPLAY / 2;
				size = MAX_PAGE_ITEM_DISPLAY;
			}
		}

		for (int i = 0; i < size; i++) {
			pageItems.add(new PageItem(start + i, (start + i) == currentNumber));
		}
	}

	public List<PageItem> getPageItems() {
		return pageItems;
	}

	public int getPreviousNumber() {
		return currentNumber - 2;
	}

	public int getCurrentNumber() {
		return currentNumber - 1;
	}

	public int getNextNumber() {
		return currentNumber;
	}

	public List<T> getContent() {
		return page.getContent();
	}

	public int getSize() {
		return page.getSize();
	}

	public int getTotalPages() {
		return page.getTotalPages();
	}

	public boolean isFirst() {
		return page.isFirst();
	}

	public boolean isLast() {
		return page.isLast();
	}

	public boolean hasPrevious() {
		return page.hasPrevious();
	}

	public boolean hasNext() {
		return page.hasNext();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public class PageItem {
		private int number;
		private boolean current;

		public PageItem(int number, boolean current) {
			this.number = number;
			this.current = current;
		}

		public int getNumber() {
			return number;
		}

		public int getActualNumber() {
			return number - 1;
		}

		public boolean isCurrent() {
			return current;
		}
	}
}
