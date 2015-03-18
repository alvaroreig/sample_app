function [C, sigma] = dataset3Params(X, y, Xval, yval)
%EX6PARAMS returns your choice of C and sigma for Part 3 of the exercise
%where you select the optimal (C, sigma) learning parameters to use for SVM
%with RBF kernel
%   [C, sigma] = EX6PARAMS(X, y, Xval, yval) returns your choice of C and 
%   sigma. You should complete this function to return the optimal C and 
%   sigma based on a cross-validation set.
%

% You need to return the following variables correctly.
C = 1;
sigma = 0.3;

% ====================== YOUR CODE HERE ======================
% Instructions: Fill in this function to return the optimal C and sigma
%               learning parameters found using the cross validation set.
%               You can use svmPredict to predict the labels on the cross
%               validation set. For example, 
%                   predictions = svmPredict(model, Xval);
%               will return the predictions on the cross validation set.
%
%  Note: You can compute the prediction error using 
%        mean(double(predictions ~= yval))
%

values= [0.01; 0.03; 0.1; 0.3; 1; 3; 10; 30];

possibleValues = zeros(size(values,1)^2,3);
counter = 1;

% Iterate over the (c,sigma) combinations and store the error
for i=1:size(values,1)
	for j=1:size(values,1)
		i
		j
		C = values(i)
		sigma = values(j)
		model= svmTrain(X, y, C, @(x1, x2) gaussianKernel(x1, x2, sigma));
		predictions = svmPredict(model, Xval);
		error = mean(double(predictions ~= yval))
		possibleValues(counter,1) = error;
		possibleValues(counter,2) = C;
		possibleValues(counter,3) = sigma;
		counter = counter +1;
	endfor
endfor

disp("Error value | C | sigma")
possibleValues

% Find out the values associated to the minimum error
[min index] = min (possibleValues(:,1),[],1)
disp("optimal values")
C = possibleValues(index,2)
sigma = possibleValues(index,3)



% =========================================================================

end
