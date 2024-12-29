package com.leetcode2.org.树状数组;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Supplier;

interface IOptions<E> {
    double[] getXs();
    double[] getYs();
    E[] getWs();
    E e();
    E op(E a, E b);
}

class BIT2DCompress<E> {
    private final Supplier<E> _e;
    private final BiFunction<E, E, E> _op;
    private int _n;
    private int _m;
    private double[] _keyX;
    private double[] _keyY;
    private E[][] _data;

    @SuppressWarnings("unchecked")
    public BIT2DCompress(IOptions<E> options) {
        double[] xs = options.getXs();
        double[] ys = options.getYs();
        this._e = options::e;
        this._op = options::op;

        // Compress X coordinates
        this._keyX = Arrays.stream(xs).distinct().sorted().toArray();
        this._n = this._keyX.length;

        // Compress Y coordinates
        this._keyY = Arrays.stream(ys).distinct().sorted().toArray();
        this._m = this._keyY.length;

        // Initialize the data structure
        this._data = (E[][]) new Object[this._n + 1][this._m + 1];
        for (int i = 0; i <= this._n; i++) {
            for (int j = 0; j <= this._m; j++) {
                this._data[i][j] = this._e.get();
            }
        }

        // If initial values are provided, initialize them
        E[] ws = options.getWs();
        if (ws != null) {
            for (int i = 0; i < xs.length; i++) {
                this.update(xs[i], ys[i], ws[i]);
            }
        }
    }

    private int _bisectLeft(double[] arr, double x) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (arr[mid] < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private int _xtoi(double x) {
        return _bisectLeft(_keyX, x);
    }

    private int _ytoi(double y) {
        return _bisectLeft(_keyY, y);
    }

    public void update(double x, double y, E val) {
        int xi = _xtoi(x) + 1;
        int yi = _ytoi(y) + 1;

        for (int i = xi; i <= _n; i += i & -i) {
            for (int j = yi; j <= _m; j += j & -j) {
                _data[i][j] = _op.apply(_data[i][j], val);
            }
        }
    }

    // Query the range [0,x) × [0,y)
    private E _queryPrefix(double x, double y) {
        E result = _e.get();
        int xi = _xtoi(x);
        int yi = _ytoi(y);

        for (int i = xi; i > 0; i -= i & -i) {
            for (int j = yi; j > 0; j -= j & -j) {
                result = _op.apply(result, _data[i][j]);
            }
        }
        return result;
    }

    // Query the range [lx,rx) × [ly,ry)
    public E query(double lx, double rx, double ly, double ry) {
        if (lx >= rx || ly >= ry) return _e.get();

        // Use inclusion-exclusion principle to calculate the result
        E result = _op.apply(
                _op.apply(
                        _queryPrefix(rx, ry),
                        _op.apply(_e.get(), _queryPrefix(lx, ry))
                ),
                _op.apply(
                        _queryPrefix(rx, ly),
                        _op.apply(_e.get(), _queryPrefix(lx, ly))
                )
        );

        return result;
    }
}

