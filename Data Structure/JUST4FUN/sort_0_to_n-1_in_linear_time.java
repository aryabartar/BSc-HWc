class Sort1ToN2Minus1 {

    void countSort(int arr[], int n, int exp) {
        int output[] = new int[n]; // output array
        int i, count[] = new int[n];
        for (i = 0; i < n; i++)
            count[i] = 0;

        for (i = 0; i < n; i++)
            count[(arr[i] / exp) % n]++;

        for (i = 1; i < n; i++)
            count[i] += count[i - 1];

        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % n] - 1] = arr[i];
            count[(arr[i] / exp) % n]--;
        }

        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }


    void radixSort(int arr[], int n) {
        countSort(arr, n, 1); //For the lsb (first bit).
        countSort(arr, n, n); // For the second bit.
    }
}
